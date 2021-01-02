package asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.objectweb.asm.Opcodes.ARETURN;
import static org.objectweb.asm.Opcodes.ASM6;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.RETURN;

/**
 * 为方法的前后添加一些内容
 *
 * @date 2018/12/28 23:28
 */

public class MethodAddCode {

    public static class AddCodeVisitor extends ClassVisitor {

        public AddCodeVisitor(int i, ClassVisitor classVisitor) {
            super(i, classVisitor);
        }

        /**
         * 访问到方法时被调用
         *
         * @param access
         * @param name
         * @param descriptor
         * @param signature
         * @param exceptions
         * @return
         */
        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
            //不代理构造函数
            if (!"<init>".equals(name)) {
                MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
                //方式一
//                return new AddCodeMethodVisitor_1(this.api, mv);

                //方式二
                 return new AddCodeMethodVisitor_2(this.api,mv);

            }
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }
    }

    /**
     * 方式一:通过字节编写字节码的方式织入代码
     */
    public static class AddCodeMethodVisitor_1 extends MethodVisitor {

        public AddCodeMethodVisitor_1(int api, MethodVisitor methodVisitor) {
            super(api, methodVisitor);
        }

        /**
         * 方法的开始,即刚进入方法里面
         */
        @Override
        public void visitCode() {
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("方式一:方法开始运行"+getClass());
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
            super.visitCode();
        }

        @Override
        public void visitInsn(int opcode) {
            if (opcode == ARETURN || opcode == RETURN) {
                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitLdcInsn("方式一:方法调用结束"+getClass());
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
            }
            super.visitInsn(opcode);
        }

        @Override
        public void visitEnd() {
            mv.visitMaxs(6, 6);
            super.visitEnd();
        }
    }

    /**
     * 方式二:通过调用现有的class文件
     */
    public static class AddCodeMethodVisitor_2 extends MethodVisitor {

        public AddCodeMethodVisitor_2(int api, MethodVisitor methodVisitor) {
            super(api, methodVisitor);
        }


        /**
         * 方法的开始,即刚进入方法里面
         */
        @Override
        public void visitCode() {
            mv.visitMethodInsn(INVOKESTATIC, Log.class.getName().replace(".", "/"), "beforeMethod", "()V", false);
            super.visitCode();
        }

        @Override
        public void visitInsn(int opcode) {
            if (opcode == ARETURN || opcode == RETURN) {
                mv.visitMethodInsn(INVOKESTATIC, Log.class.getName().replace(".", "/"), "afterMethod", "()V", false);
            }
            super.visitInsn(opcode);
        }

        @Override
        public void visitEnd() {
            mv.visitMaxs(6, 6);
            super.visitEnd();
        }
    }

    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String fullName = Demo.class.getName();
        String fullNameType = fullName.replace(".", "/");
        ClassReader cr = new ClassReader(fullNameType);
        ClassWriter cw = new ClassWriter(0);
        AddCodeVisitor cv = new AddCodeVisitor(ASM6, cw);
        cr.accept(cv, ClassReader.SKIP_DEBUG);
        byte[] bytes = cw.toByteArray();

        MyClassLoader classLoader = new MyClassLoader();
        Class<?> cls = classLoader.defineClassPublic(fullName, bytes, 0, bytes.length);
//        Class<?> cls =classLoader.loadClass(fullName);

        Object o = cls.newInstance();
        Method getDemoInfo = cls.getMethod("getDemoInfo");
        getDemoInfo.invoke(o);
    }
}
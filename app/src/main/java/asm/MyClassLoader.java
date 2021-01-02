package asm;

class MyClassLoader  extends ClassLoader {
    public Class<?> defineClassPublic(String fullName, byte[] bytes, int i, int length) {
        return defineClass(fullName, bytes, 0, length);
    }

    @Override
    public Class<?> loadClass(String name)  {
        try {
            return super.loadClass(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

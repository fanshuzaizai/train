package com.github.fanshuzaizai.interview.classLoad;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomClassLoader extends ClassLoader {
    private final String path;

    public CustomClassLoader(String path) {
        super();
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Path path = Paths.get(this.path, name);
        try {
            ByteBuffer buffer = ByteBuffer.allocate(2048);
            FileChannel fileChannel = FileChannel.open(path);
            int size = (int) fileChannel.size();

            int read = fileChannel.read(buffer);
            byte[] array = buffer.array();
            Class<?> aClass = defineClass(null, array, 0, size);

            return aClass;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        CustomClassLoader customClassLoader = new CustomClassLoader("C:\\Users\\fanshuzai\\Desktop");
        Class<?> aClass = customClassLoader.loadClass("Cat.class");
        System.out.println(aClass);
        System.out.println(aClass.getClassLoader());
        Object o = aClass.newInstance();

    }
}

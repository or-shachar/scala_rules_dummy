package com.example;

import java.io.File;

public class DirTree {
    private static String getIndentString(int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            sb.append("|  ");
        }
        return sb.toString();
    }

    public static String calculate(File folder) {
        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("folder is not a Directory");
        }
        int indent = 0;
        StringBuilder sb = new StringBuilder();
        calculate(folder, indent, sb,true);
        return sb.toString();
    }

    private static void printFile(File file, int indent, StringBuilder sb) {
        sb.append(getIndentString(indent));
        sb.append("+--");
        sb.append(file.getName());
        sb.append("\n");
    }

    private static void calculate(File folder, int indent,
                                  StringBuilder sb, boolean keepDigging) {
        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("folder is not a Directory");
        }
        sb.append(getIndentString(indent));
        sb.append("+--");
        sb.append(folder.getName());
        sb.append("/");
        if (!keepDigging) {
            sb.append("...\n");
            return;
        }
            sb.append("\n");

        File[] files = folder.listFiles();
        if (files!=null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    calculate(file, indent + 1, sb, !"external".equals(folder.getName()));
                } else {
                    printFile(file, indent + 1, sb);
                }
            }
        }

    }

    public static void main(String[] args) {
        String cwd = System.getProperty("user.dir");
        System.out.println("CURRENT DIR: "+cwd);
        System.out.println("=============TREE===============");
        System.out.println(calculate(new File(cwd)));
    }
}

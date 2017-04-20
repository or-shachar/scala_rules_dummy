package com.example;

import java.io.File;

public class JavaBinary {
    private static String getIndentString(int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            sb.append("|  ");
        }
        return sb.toString();
    }
    public static String printDirectoryTree(File folder) {
        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("folder is not a Directory");
        }
        int indent = 0;
        StringBuilder sb = new StringBuilder();
        printDirectoryTree(folder, indent, sb,true);
        return sb.toString();
    }

    private static void printFile(File file, int indent, StringBuilder sb) {
        sb.append(getIndentString(indent));
        sb.append("+--");
        sb.append(file.getName());
        sb.append("\n");
    }

    private static void printDirectoryTree(File folder, int indent,
                                           StringBuilder sb,boolean keepDigging) {
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

        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                printDirectoryTree(file, indent + 1, sb,!"external".equals(folder.getName()));
            } else {
                printFile(file, indent + 1, sb);
            }
        }

    }

    public static void main(String[] args) {
        String cwd = System.getProperty("user.dir");
        System.out.println("CURRENT DIR: "+cwd);
        System.out.println("=============TREE===============");
        System.out.println(printDirectoryTree(new File(cwd)));
    }
}

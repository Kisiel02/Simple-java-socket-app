package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Client
{
    public static void main(String[] args) throws IOException
    {
        int numberOfFiles = 5;

        ArrayList<File> files = new ArrayList<>();
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 1; i <= numberOfFiles; i++)
        {
            files.add(new File(".\\source", Integer.toString(i) + ".jpg"));
        }

        for (int i = 0; i < numberOfFiles; i++)
        {
            Thread upload = new Upload(files.get(i));
            threads.add(upload);
            upload.start();
        }
    }
}

package org.example;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Upload extends Thread
{
    private File file;

    public Upload(File file)
    {
        this.file = file;
    }

    @Override
    public void run()
    {
        System.out.println("Sending data " + file.getName() + " in new thread");
        try (Socket server = new Socket(InetAddress.getLocalHost(), 5056);
             DataOutputStream dos = new DataOutputStream(server.getOutputStream());
             FileInputStream fis = new FileInputStream(file))
        {
            dos.writeUTF(file.getName());
            dos.writeLong(file.length());

            byte[] buffer = new byte[1024]; //buffer 1kb

            long alreadyReaden = 0, size = file.length();

            while (alreadyReaden != size)
            {
                int read = fis.read(buffer);
                dos.write(buffer, 0, read);
                alreadyReaden += read;
            }

            System.out.println("End of sending " + file.getName());

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

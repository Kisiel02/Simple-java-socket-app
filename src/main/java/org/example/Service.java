package org.example;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;
import java.util.Random;

public class Service extends Thread
{

    final Socket client;

    public Service(Socket client)
    {
        this.client = client;
    }

    @Override
    public void run()
    {
        try (DataInputStream dis = new DataInputStream(client.getInputStream());
        )
        {
            Random generator = new Random();
            int wait = generator.nextInt(7000) + 3000; //waiting for 3-10 seconds

            String name = null;
            long size = 0;

            name = dis.readUTF(); //filename
            size = dis.readLong(); //file size

            System.out.println("Filename: " + name);
            System.out.println("File size: " + size);

            sleep(wait); //advanced save simulation

            File file = new File(".\\saved", name);

            try (FileOutputStream fos = new FileOutputStream(file))
            {
                long alreadyReaden = 0;         //how many bytes have been arleady readen
                int readen = 0;                 //how many bytes have been read now
                byte[] buffer = new byte[1024]; //buffer 1kb

                while (alreadyReaden < size)
                {
                    readen = dis.read(buffer);
                    alreadyReaden += readen;
                    fos.write(buffer, 0, readen);
                }
                System.out.println("Saving complete: " + file.getName());
            }

            System.out.println("Disconnecting client: " + client + "who sent " + file.getName());
            this.client.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}


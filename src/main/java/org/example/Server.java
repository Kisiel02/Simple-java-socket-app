package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static void main(String[] args)
    {
        try (ServerSocket server = new ServerSocket(5056))
        {
            System.out.println("Opening server");
            while (true)
            {
                Socket socket = server.accept(); // connecting

                System.out.println("New client: " + socket + ", opening new thread for him");
                Thread service = new Service(socket);
                service.start();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
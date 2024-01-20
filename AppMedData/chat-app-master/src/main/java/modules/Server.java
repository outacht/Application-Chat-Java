package modules;

import modules.client.GestionnaireClients;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private static Server server;

    private List<GestionnaireClients> clients = new ArrayList<>();

    private Server() throws IOException {
        serverSocket = new ServerSocket(3001);
    }

    public static Server getInstance() throws IOException {
        return server!=null? server:(server=new Server());
    }

    public void makeSocket(){
        while (!serverSocket.isClosed()){
            try{
                socket = serverSocket.accept();
                GestionnaireClients gestionnaireClients = new GestionnaireClients(socket,clients);
                clients.add(gestionnaireClients);
                System.out.println("modules.server.client socket accepted "+socket.toString());
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}

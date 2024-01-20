package modules.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class GestionnaireClients {
    private Socket socket;
    private List<GestionnaireClients> clients;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String msg = "";

    public GestionnaireClients(Socket socket, List<GestionnaireClients> clients) {
        try {
            this.socket = socket;
            this.clients = clients;
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (socket.isConnected()) {
                        msg = dataInputStream.readUTF();
                        for (GestionnaireClients gestionnaireClients : clients) {
                            if (gestionnaireClients.socket.getPort() != socket.getPort()) {
                                gestionnaireClients.dataOutputStream.writeUTF(msg);
                                gestionnaireClients.dataOutputStream.flush();
                            }
                        }
                    }
                }catch (IOException e){
                    e.printStackTrace();

                }
            }
        }).start();
    }
}

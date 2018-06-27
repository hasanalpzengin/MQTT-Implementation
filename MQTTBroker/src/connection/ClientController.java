/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import client.Client;
import java.net.SocketAddress;
import java.util.ArrayList;

public class ClientController extends Thread{
    ArrayList<Client> clients;

    private void addClient(Client client){
        clients.add(client);
    }
    
    private void removeClient(SocketAddress address){
        for(Client client : clients){
            
        }
    }

    public ClientController() {
        clients = new ArrayList<>();
    }
    
    @Override
    public void run() {
        while(true){
            
        }
    }
    
}

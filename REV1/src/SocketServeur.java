
import java.io.*;
import java.net.*;
import java.util.Scanner;
public class SocketServeur {
    public static void main(String argv[]) {
        int port = 0;
        Scanner keyb = new Scanner(System.in);
        // demander la saisie qui dun numero de port
        System.out.print("Port d'écoute : ");
        try {
            port = keyb.nextInt();
        } catch (NumberFormatException e) {
            System.err.println("Le paramètre n'est pas un entier.");
            System.err.println("Usage : java ServeurUDP port-serveur");
            System.exit(-1);
        }
        try {
            // la creation dun socket doit etre dans un try catch
            ServerSocket serverSocket = new ServerSocket(port);
            // creatio dune socket en lui attribuant le numero de port saisie par luser

            Socket socket = serverSocket.accept();
            // attendre &  accepter la requete venante au socket
            ObjectOutputStream output =
                    new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input =
                    new ObjectInputStream(socket.getInputStream());
            // elle va nous permettre de lire les objets java et non pas des bits seulement
            String chaine = (String) input.readObject();
            System.out.println(" recu : " + chaine);
            // pour induquer au user qon a recu son msg plutot le serveur a bien recu
            System.out.println(" ca vient de : " + socket.getInetAddress() +
                    ":" + socket.getPort());
            // pour lui indiquer l'adresse de la socket et le num de port
            output.writeObject(new String("bien recu"));
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
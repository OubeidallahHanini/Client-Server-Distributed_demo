
import java.io.*;
import java.net.*;
import java.util.Scanner;
class SocketClient  {
    public static void main(String argv[]) {
        int port = 0;
        String host = "";
        Scanner keyb = new Scanner(System.in);
        // demander au serveur de saisir de ladr ip
        System.out.print("Nom du serveur : ");
        host = keyb.next();
        System.out.print("Port du serveur : ");

        try {
            port = keyb.nextInt();
        } catch (NumberFormatException e) {
            System.err.println("Le second paramètre n'est pas un entier.");
            System.exit(-1);
        }
        // faire la conversion du text saisie pour sassurer quil sagit bien dun entier IP
        try {
            //
            InetAddress adr = InetAddress.getByName(host);
            // recuperer ladresse IP a partir du host donner
            Socket socket = new Socket(adr, port);
            // creation dune socket en lui attribuant ladre et port
            ObjectOutputStream output =
                    new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input =
                    new ObjectInputStream(socket.getInputStream());
            // elle va nous permettre de lire les objets java et non pas des bits seulement
            output.writeObject(new String("ma première socket"));
            // transmettre le msg a envoyer au serveur
            String chaine = (String) input.readObject();
            System.out.println(" recu du serveur : " + chaine);
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }


}
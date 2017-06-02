package br.com.eduardo.fatesg.jrmi;

/**
 *
 * @author eduardo
 */
public class App {

    public static void main(String[] args) {
        int port = 0;
        boolean server = false;

        boolean client = false;
        String host = "";
        String pasta = "";
        String user = "";

        String[] peers = {};

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-server":
                    server = true;
                    break;
                case "-peers":
                    peers = args[++i].split(";");
                    break;
                case "-h":
                    host = args[++i];
                    break;
                case "-d":
                    pasta = args[++i];
                    break;
                case "-u":
                    user = args[++i];
                    break;
                case "-p":
                    port = Integer.parseInt(args[++i]);
                    break;
                case "-client":
                    client = true;
                    break;
            }
        }

        if (server) {
            RegistryServer r = new RegistryServer(port);

            r.start();

            System.out.println("Iniciado servidor na porta " + port);

            r.mostraMenu();
        } else if (client) {
            Peer p = new Peer(host, port, pasta, user, peers);

            p.start();

            p.mostraMenu();
        }
    }
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private BufferedReader buffReader;
    private BufferedWriter buffWriter;
    private String name;

    public Client(Socket socket, String name) {
        try {
            this.socket = socket;
            this.buffWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.buffReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.name = name;
        } catch (IOException e) {
            e.printStackTrace();
            closeAll(); // Llamar a closeAll() en caso de error
        }
    }

    public void sendMessage() {
        try {
            buffWriter.write(name);
            buffWriter.newLine();
            buffWriter.flush();
            Scanner sc = new Scanner(System.in);
            while (socket.isConnected()) {
                String messageToSend = sc.nextLine();
                buffWriter.write(name + ": " + escribir(messageToSend));
                buffWriter.newLine();
                buffWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            closeAll();
        }
    }

    public void readMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String messageFromServer;
                try {
                    while (socket.isConnected() && (messageFromServer = buffReader.readLine()) != null) {
                        System.out.println(messageFromServer);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    closeAll();
                }
            }
        }).start();
    }

    public void closeAll() {
        try {
            if (buffReader != null) {
                buffReader.close();
            }
            if (buffWriter != null) {
                buffWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String escribir(String mnsj) {
        String mnsjN = "";

        int desplazamiento = 3; // Cambia esto para ajustar el cifrado

        for (int i = 0; i<mnsj.length(); i++){

            String c ="";

            if (mnsj.charAt(i) == 'a') {
                c = "O2";
            } else if (mnsj.charAt(i) == 'A') {
                c = "2T";
            } else if (mnsj.charAt(i) == 'b') {
                c = "G1";
            } else if (mnsj.charAt(i) == 'B') {
                c = "2G";
            } else if (mnsj.charAt(i) == 'c') {
                c = "J1";
            } else if (mnsj.charAt(i) == 'C') {
                c = "2S";
            } else if (mnsj.charAt(i) == 'd') {
                c = "I1";
            } else if (mnsj.charAt(i) == 'D') {
                c = "2Y";
            } else if (mnsj.charAt(i) == 'e') {
                c = "C1";
            } else if (mnsj.charAt(i) == 'E') {
                c = "2B";
            } else if (mnsj.charAt(i) == 'f') {
                c = "A1";
            } else if (mnsj.charAt(i) == 'F') {
                c = "2R";
            } else if (mnsj.charAt(i) == 'g') {
                c = "S1";
            } else if (mnsj.charAt(i) == 'G') {
                c = "2I";
            } else if (mnsj.charAt(i) == 'h') {
                c = "Z1";
            } else if (mnsj.charAt(i) == 'H') {
                c = "2Q";
            } else if (mnsj.charAt(i) == 'i') {
                c = "N1";
            } else if (mnsj.charAt(i) == 'I') {
                c = "1q";
            } else if (mnsj.charAt(i) == 'j') {
                c = "F1";
            } else if (mnsj.charAt(i) == 'J') {
                c = "1X";
            } else if (mnsj.charAt(i) == 'k') {
                c = "T1";
            } else if (mnsj.charAt(i) == 'K') {
                c = "1Z";
            } else if (mnsj.charAt(i) == 'l') {
                c = "E0";
            } else if (mnsj.charAt(i) == 'L') {
                c = "1G";
            } else if (mnsj.charAt(i) == 'm') {
                c = "W0";
            } else if (mnsj.charAt(i) == 'M') {
                c = "1O";
            } else if (mnsj.charAt(i) == 'n') {
                c = "M0";
            } else if (mnsj.charAt(i) == 'N') {
                c = "1Y";
            } else if (mnsj.charAt(i) == 'ñ') {
                c = "V0";
            } else if (mnsj.charAt(i) == 'Ñ') {
                c = "1S";
            } else if (mnsj.charAt(i) == 'o') {
                c = "X0";
            } else if (mnsj.charAt(i) == 'O') {
                c = "1J";
            } else if (mnsj.charAt(i) == 'p') {
                c = "H0";
            } else if (mnsj.charAt(i) == 'P') {
                c = "1I";
            } else if (mnsj.charAt(i) == 'q') {
                c = "D0";
            } else if (mnsj.charAt(i) == 'Q') {
                c = "1Q";
            } else if (mnsj.charAt(i) == 'r') {
                c = "P0";
            } else if (mnsj.charAt(i) == 'R') {
                c = "0q";
            } else if (mnsj.charAt(i) == 's') {
                c = "L0";
            } else if (mnsj.charAt(i) == 'S') {
                c = "0W";
            } else if (mnsj.charAt(i) == 't') {
                c = "K2";
            } else if (mnsj.charAt(i) == 'T') {
                c = "0P";
            } else if (mnsj.charAt(i) == 'u') {
                c = "R2";
            } else if (mnsj.charAt(i) == 'U') {
                c = "0G";
            } else if (mnsj.charAt(i) == 'v') {
                c = "Y2";
            } else if (mnsj.charAt(i) == 'V') {
                c = "0S";
            } else if (mnsj.charAt(i) == 'w') {
                c = "U2";
            } else if (mnsj.charAt(i) == 'W') {
                c = "0E";
            } else if (mnsj.charAt(i) == 'x') {
                c = "Q2";
            } else if (mnsj.charAt(i) == 'X') {
                c = "0B";
            } else if (mnsj.charAt(i) == 'y') {
                c = "B2";
            } else if (mnsj.charAt(i) == 'Y') {
                c = "0C";
            } else if (mnsj.charAt(i) == 'z') {
                c = "J2";
            } else if (mnsj.charAt(i) == 'Z') {
                c = "0I";
            } else if (mnsj.charAt(i) == ' ') {
                c = "00";
            } else if (mnsj.charAt(i) == '1') {
                c = "Sz";
            } else if (mnsj.charAt(i) == '2') {
                c = "Ay";
            } else if (mnsj.charAt(i) == '3') {
                c = "Fx";
            } else if (mnsj.charAt(i) == '4') {
                c = "Ew";
            } else if (mnsj.charAt(i) == '5') {
                c = "If";
            } else if (mnsj.charAt(i) == '6') {
                c = "Te";
            } else if (mnsj.charAt(i) == '7') {
                c = "Od";
            } else if (mnsj.charAt(i) == '8') {
                c = "Lc";
            } else if (mnsj.charAt(i) == '9') {
                c = "Ub";
            } else if (mnsj.charAt(i) == '0') {
                c = "Da";
            } else {
                c = "";
            }


            mnsjN = mnsjN + c ;


        }
        return mnsjN;
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa tu mensaje");
        String name = sc.nextLine();
        String cifrado = escribir(name); // Declara "cifrado" aquí
        Socket socket = new Socket("localhost", 1234);
        Client client = new Client(socket, cifrado);
        client.readMessage();
        client.sendMessage();
    }
}

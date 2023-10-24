package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    public Socket socket;
    private BufferedReader buffReader;
    private BufferedWriter buffWriter;
    private String name;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.buffWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.buffReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.name = buffReader.readLine();
            clientHandlers.add(this);
            boradcastMessage("Servidor " + decryptMessage(name) + " ha entrado en el chat");
        } catch (IOException e) {
            closeAll(socket, buffReader, buffWriter);
        }
    }

    @Override
    public void run() {
        String messageFromClient;
        while (socket.isConnected()) {
            try {
                messageFromClient = buffReader.readLine();
                boradcastMessage(messageFromClient);
            } catch (IOException e) {
                closeAll(socket, buffReader, buffWriter);
                break;
            }
        }
    }

    public void boradcastMessage(String messageToSend) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if (!clientHandler.name.equals(name)) {
                    clientHandler.buffWriter.write(messageToSend);
                    clientHandler.buffWriter.newLine();
                    clientHandler.buffWriter.flush();
                }
            } catch (IOException e) {
                closeAll(socket, buffReader, buffWriter);
            }
        }
    }

    public void removeClientHandler() {
        clientHandlers.remove(this);
        boradcastMessage("Servidor " + name + " Se ha ido");
    }

    public void closeAll(Socket socket, BufferedReader buffReader, BufferedWriter buffWriter) {
        removeClientHandler();
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
            e.getStackTrace();
        }
    }

    public String decryptMessage(String mnsj) {
        String mnsjN = "";

        for (int i = 0; i < ((mnsj.length())); i = i + 2) {
            String c = Character.toString(mnsj.charAt(i)) + Character.toString(mnsj.charAt(i + 1));

            char letra = 'a';

            if (c.equals("O2")) {
                    letra = 'a';
                } else if (c.equals("2T")) {
                    letra = 'A';
                } else if (c.equals("G1")) {
                    letra = 'b';
                } else if (c.equals("2G")) {
                    letra = 'B';
                } else if (c.equals("J1")) {
                    letra = 'c';
                } else if (c.equals("2S")) {
                    letra = 'C';
                } else if (c.equals("I1")) {
                    letra = 'd';
                } else if (c.equals("2Y")) {
                    letra = 'D';
                } else if (c.equals("C1")) {
                    letra = 'e';
                } else if (c.equals("2B")) {
                    letra = 'E';
                } else if (c.equals("A1")) {
                    letra = 'f';
                } else if (c.equals("2R")) {
                    letra = 'F';
                } else if (c.equals("S1")) {
                    letra = 'g';
                } else if (c.equals("2I")) {
                    letra = 'G';
                } else if (c.equals("Z1")) {
                    letra = 'h';
                } else if (c.equals("2Q")) {
                    letra = 'H';
                } else if (c.equals("N1")) {
                    letra = 'i';
                } else if (c.equals("1q")) {
                    letra = 'I';
                } else if (c.equals("F1")) {
                    letra = 'j';
                } else if (c.equals("1X")) {
                    letra = 'J';
                } else if (c.equals("T1")) {
                    letra = 'k';
                } else if (c.equals("1Z")) {
                    letra = 'K';
                } else if (c.equals("E0")) {
                    letra = 'l';
                } else if (c.equals("1G")) {
                    letra = 'L';
                } else if (c.equals("W0")) {
                    letra = 'm';
                } else if (c.equals("1O")) {
                    letra = 'M';
                } else if (c.equals("M0")) {
                    letra = 'n';
                } else if (c.equals("1Y")) {
                    letra = 'N';
                } else if (c.equals("V0")) {
                    letra = 'ñ';
                } else if (c.equals("1S")) {
                    letra = 'Ñ';
                } else if (c.equals("X0")) {
                    letra = 'o';
                } else if (c.equals("1J")) {
                    letra = 'O';
                } else if (c.equals("H0")) {
                    letra = 'p';
                } else if (c.equals("1I")) {
                    letra = 'P';
                } else if (c.equals("D0")) {
                    letra = 'q';
                } else if (c.equals("1Q")) {
                    letra = 'Q';
                } else if (c.equals("P0")) {
                    letra = 'r';
                } else if (c.equals("0q")) {
                    letra = 'R';
                } else if (c.equals("L0")) {
                    letra = 's';
                } else if (c.equals("0W")) {
                    letra = 'S';
                } else if (c.equals("K2")) {
                    letra = 't';
                } else if (c.equals("0P")) {
                    letra = 'T';
                } else if (c.equals("R2")) {
                    letra = 'u';
                } else if (c.equals("0G")) {
                    letra = 'U';
                } else if (c.equals("Y2")) {
                    letra = 'v';
                } else if (c.equals("0S")) {
                    letra = 'V';
                } else if (c.equals("U2")) {
                    letra = 'w';
                } else if (c.equals("0E")) {
                    letra = 'W';
                } else if (c.equals("Q2")) {
                    letra = 'x';
                } else if (c.equals("0B")) {
                    letra = 'X';
                } else if (c.equals("B2")) {
                    letra = 'y';
                } else if (c.equals("0C")) {
                    letra = 'Y';
                } else if (c.equals("J2")) {
                    letra = 'z';
                } else if (c.equals("0I")) {
                    letra = 'Z';
                } else if (c.equals("00")) {
                    letra = ' ';
                }else if (c.equals("Sz")) {
                    letra = '1';
                } else if (c.equals("Ay")) {
                    letra = '2';
                } else if (c.equals("Fx")) {
                    letra = '3';
                } else if (c.equals("Ew")) {
                    letra = '4';
                } else if (c.equals("If")) {
                    letra = '5';
                } else if (c.equals("Te")) {
                    letra = '6';
                } else if (c.equals("Od")) {
                    letra = '7';
                } else if (c.equals("Lc")) {
                    letra = '8';
                } else if (c.equals("Ub")) {
                    letra = '9';
                } else if (c.equals("Da")) {
                    letra = '0';
                } else {
                    letra = '\0'; // Esto significa que no se encontró una correspondencia.
                } // ... (agrega todos los demás casos)

            mnsjN = mnsjN + Character.toString(letra);
        }

        return mnsjN;
    }
}

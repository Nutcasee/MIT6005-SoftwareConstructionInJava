/* Copyright (c) 2007-2017 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package minesweeper.server;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;

import org.junit.Test;
import static org.junit.Assert.*;




/**
 * TODO
 */
public class MinesweeperServerTest {
    
    private static final String LOCALHOST = "127.0.0.1";
    private static int PORT;
    private static final int MAX_CONNECTION_ATTEMPS = 10;
    private static final String BOARDS_PKG = "minesweeper/server/boards/";
    
    
    private static Thread startMinesweeperServer(String boardFile) throws IOException {
        PORT = 4000 + new Random().nextInt(1 << 15);

        final URL boardURL = ClassLoader.getSystemClassLoader().getResource(BOARDS_PKG + boardFile);

        if (boardURL == null) {
            throw new IOException("Fail to locate resource" + boardURL);
        }
        String boardPath;
        try {
            boardPath = new File(boardURL.toURI()).getAbsolutePath();
        } catch (URISyntaxException urise) {
            throw new IOException("Invalid URL " + boardURL, urise); 
        }
        
        final String[] args = new String[] {
                "--debug",
                "--port", Integer.toString(PORT),
                "--file", boardPath
        };
        Thread serverThread = new Thread(() -> MinesweeperServer.main(args));
        serverThread.start();
        return serverThread;
    }
    
    private static Thread startMinesweeperServerBySize() throws IOException {
        PORT = 4000 + new Random().nextInt(1 << 15);

        String sizeX_sizeY = "123,234";
        String sizeY = "234";
        final String[] args = new String[] {
                "--port", Integer.toString(PORT),
                "--size", sizeX_sizeY
        };
        Thread serverThread = new Thread(() -> MinesweeperServer.main(args));
        serverThread.start();
        return serverThread;
    }
    
    private static Socket connectToMinesweeperServer(Thread server) throws IOException {
        int attempts = 0;
        while (true) {
            try {
                Socket socket = new Socket(LOCALHOST, PORT);
                socket.setSoTimeout(3000);
                return socket;
            } catch(ConnectException ce) {
                if ( ! server.isAlive()) {
                    throw new IOException("Server thread is not running");
                }
                if (++attempts > MAX_CONNECTION_ATTEMPS) {
                    throw new IOException("Exceeded max connection attemps");
                }
                try {Thread.sleep(attempts *10); } catch(InterruptedException ie) {};
            }
        }
    }
    
    @Test(timeout = 10000)
    public void testStartBoardBySize() throws IOException {
        Thread thread = startMinesweeperServerBySize();
        Socket socket = connectToMinesweeperServer(thread);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        assertTrue("expected Hello message", in.readLine().startsWith("Welcome"));

        out.println("bye"); 
        socket.close();
        
    }

    
//    @Test(timeout = 10000)
//    public void testSmall() throws IOException {
//        Thread thread = startMinesweeperServer("test1.txt");
//        Socket socket = connectToMinesweeperServer(thread);
//        
//        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//
//        assertTrue("expected Hello message", in.readLine().startsWith("Welcome"));
//
////        out.println("look");
////        assertEquals("- - -", in.readLine());
////        assertEquals("- - -", in.readLine());
////        assertEquals("- - -", in.readLine());
////        assertEquals("- - -", in.readLine());
//
////        out.println("dig 2 0");
////        assertEquals("- - 1", in.readLine());
////        assertEquals("- - -", in.readLine());
////        assertEquals("- - -", in.readLine());
////        assertEquals("- - -", in.readLine());
////        
////        out.println("dig 1 1");
////        assertEquals("BOOM!", in.readLine());
//        
////        out.println("look"); // debug mode is on
//        
////        assertEquals("     ", in.readLine());
////        assertEquals("     ", in.readLine());
////        assertEquals("     ", in.readLine());
////        assertEquals("     ", in.readLine());
//
//        out.println("bye"); 
//        socket.close();
//        
//    }
//    
//    @Test(timeout = 10000)
//    public void testBoard() throws IOException {
//        Thread thread = startMinesweeperServer("board");
//        Socket socket = connectToMinesweeperServer(thread);
//
//        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//
//        assertTrue("expected HELLO message", in.readLine().startsWith("Welcome"));
//
//        out.println("look");
//        assertEquals("- - - - - - -", in.readLine());
//        assertEquals("- - - - - - -", in.readLine());
//        assertEquals("- - - - - - -", in.readLine());
//        assertEquals("- - - - - - -", in.readLine());
//        assertEquals("- - - - - - -", in.readLine());
//        assertEquals("- - - - - - -", in.readLine());
//        assertEquals("- - - - - - -", in.readLine());
//
//        out.println("dig 3 1");
//        assertEquals("- - - - - - -", in.readLine());
//        assertEquals("- - - 1 - - -", in.readLine());
//        assertEquals("- - - - - - -", in.readLine());
//        assertEquals("- - - - - - -", in.readLine());
//        assertEquals("- - - - - - -", in.readLine());
//        assertEquals("- - - - - - -", in.readLine());
//        assertEquals("- - - - - - -", in.readLine());
//
//        out.println("dig 4 1");
//        assertEquals("BOOM!", in.readLine());
//
//        out.println("look"); // debug mode is on
//        assertEquals("             ", in.readLine());
//        assertEquals("             ", in.readLine());
//        assertEquals("             ", in.readLine());
//        assertEquals("             ", in.readLine());
//        assertEquals("             ", in.readLine());
//        assertEquals("1 1          ", in.readLine());
//        assertEquals("- 1          ", in.readLine());
//
//        out.println("bye");
//        socket.close();
//    }
//    
//    
//    @Test(timeout = 10000)
//    public void testMultiConnection() throws IOException {
//        Thread thread = startMinesweeperServer();
//        Socket socket = connectToMinesweeperServer(thread);
//        Socket socket2 = connectToMinesweeperServer(thread);
//        
//        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//
//        BufferedReader in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
//        PrintWriter out2 = new PrintWriter(socket2.getOutputStream(), true);

//        System.out.println("in.readLine(): " + in.readLine());
//        System.out.println("in2.readLine(): " + in2.readLine());
//        
//        assertTrue("expected Hello message", in.readLine().startsWith("Welcome to Minesweeper"));
//        assertTrue("expected Hello message", in2.readLine().startsWith("Welcome to Minesweeper"));
//
//        out.println("look");
//        
//        System.out.print("in.readLine() of out.println(look): " + in.readLine());
//        assertEquals("- - -", in.readLine());
//        assertEquals("- - -", in.readLine());

//        out.println("bye"); 
//        socket.close();
//    }
}
    
    
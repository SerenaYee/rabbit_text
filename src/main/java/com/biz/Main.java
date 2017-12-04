package com.biz;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Main  {
    public static void main(String[] args) throws IOException,TimeoutException {
        Recv.receiveMessage();
        Send.sendMessage();
    }

}
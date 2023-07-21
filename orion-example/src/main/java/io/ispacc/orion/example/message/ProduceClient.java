package io.ispacc.orion.example.message;

public class ProduceClient {

    public static void main(String[] args) throws Exception {
        MqClient.produce("SEND:Hello World");
    }

}

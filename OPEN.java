rivate void initConnection()
{
  // Factory
  factory = AmqpClientFactory.createAmqpClientFactory();
         
  try {
    // Client
    client = factory.createAmqpClient();
             
    // Connection listeners
    client.addConnectionListener( new ConnectionListener() {
             
      // Connecting
      public void onConnecting( ConnectionEvent ce )
      {
        EventQueue.invokeLater( new Runnable() {
          public void run()
          {
            System.out.println( &amp;quot;Connecting...&amp;quot; );
          }
         } );
       }
                 
      // Error
      public void onConnectionError( ConnectionEvent ce )
      {
        EventQueue.invokeLater( new Runnable() {
          public void run()
          {
            System.out.println( &amp;quot;Connection error.&amp;quot; );
          }
        } );
      }                            
                 
      // Open
      public void onConnectionOpen( ConnectionEvent ce )
      {
        EventQueue.invokeLater( new Runnable() {
          public void run()
          {
            System.out.println( &amp;quot;Connection open.&amp;quot; );
                             
            // Setup publisher
            doClientOpen();
          }
        } );
      }
                 
      // Close
      public void onConnectionClose( ConnectionEvent ce )
      {
        EventQueue.invokeLater( new Runnable() {
          public void run()
          {
            System.out.println( &amp;quot;Connection closed.&amp;quot; );
          }
        } );
      }
    } );
             
    // Connect to server
    client.connect(
      &amp;quot;wss://demos.kaazing.com/amqp&amp;quot;,
      &amp;quot;/&amp;quot;,
      &amp;quot;guest&amp;quot;,
      &amp;quot;guest&amp;quot;
    );
  } catch( Exception e ) {
    e.printStackTrace();
  }
}

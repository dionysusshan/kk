//diffe
class diffie {
	
	private static long power(long a, long b, long p)
	{
		if (b == 1)
			return a;
		else
			return (((long)Math.pow(a, b)) % p);
	}

	
	public static void main(String[] args)
	{
		long P, G, x, a, y, b, ka, kb;
		
		P = 23;
		System.out.println("The value of P:" + P);

		
		G = 9;
		System.out.println("The value of G:" + G);

		
		a = 4;
		System.out.println("The private key a for Alice:"
						+ a);

		x = power(G, a, P);

		
		b = 3;
		System.out.println("The private key b for Bob:"
						+ b);

		
		y = power(G, b, P);

		
		ka = power(y, a, P); 
		kb = power(x, b, P); 

		System.out.println("Secret key for the Alice is:"
						+ ka);
		System.out.println("Secret key for the Bob is:"
						+ kb);
	}
}

--
// A Java program for a Client
import java.io.*;
import java.net.*;

public class Client {

	private Socket socket = null;
	private DataInputStream input = null;
	private DataOutputStream out = null;


	public Client(String address, int port)
	{
		
		try {
			socket = new Socket(address, port);
			System.out.println("Connected");

			
			input = new DataInputStream(System.in);

			
			out = new DataOutputStream(
				socket.getOutputStream());
		}
		catch (UnknownHostException u) {
			System.out.println(u);
			return;
		}
		catch (IOException i) {
			System.out.println(i);
			return;
		}

		
		String line = "";

		
		while (!line.equals("Over")) {
			try {
				line = input.readLine();
				out.writeUTF(line);
			}
			catch (IOException i) {
				System.out.println(i);
			}
		}

		
		try {
			input.close();
			out.close();
			socket.close();
		}
		catch (IOException i) {
			System.out.println(i);
		}
	}

	public static void main(String args[])
	{
		Client client = new Client("127.0.0.1", 5000);
	}
}
---
// A Java program for a Server
import java.net.*;
import java.io.*;

public class Server
{
	
	private Socket		 socket = null;
	private ServerSocket server = null;
	private DataInputStream in	 = null;

	
	public Server(int port)
	{
		
		try
		{
			server = new ServerSocket(port);
			System.out.println("Server started");

			System.out.println("Waiting for a client ...");

			socket = server.accept();
			System.out.println("Client accepted");

			
			in = new DataInputStream(
				new BufferedInputStream(socket.getInputStream()));

			String line = "";

			
			while (!line.equals("Over"))
			{
				try
				{
					line = in.readUTF();
					System.out.println(line);

				}
				catch(IOException i)
				{
					System.out.println(i);
				}
			}
			System.out.println("Closing connection");

			
			socket.close();
			in.close();
		}
		catch(IOException i)
		{
			System.out.println(i);
		}
	}

	public static void main(String args[])
	{
		Server server = new Server(5000);
	}
}
--
// RSA 
import java.math.*;
import java.util.*;

class RSA {
	public static void main(String args[])
	{
		int p, q, n, z, d = 0, e, i;

		
		int msg = 12;
		double c;
		BigInteger msgback;

		
		p = 3;

		
		q = 11;
		n = p * q;
		z = (p - 1) * (q - 1);
		System.out.println("the value of z = " + z);

		for (e = 2; e < z; e++) {

			
			if (gcd(e, z) == 1) {
				break;
			}
		}
		System.out.println("the value of e = " + e);
		for (i = 0; i <= 9; i++) {
			int x = 1 + (i * z);

			
			if (x % e == 0) {
				d = x / e;
				break;
			}
		}
		System.out.println("the value of d = " + d);
		c = (Math.pow(msg, e)) % n;
		System.out.println("Encrypted message is : " + c);

		
		BigInteger N = BigInteger.valueOf(n);

		
		BigInteger C = BigDecimal.valueOf(c).toBigInteger();
		msgback = (C.pow(d)).mod(N);
		System.out.println("Decrypted message is : "
						+ msgback);
	}

	static int gcd(int e, int z)
	{
		if (e == 0)
			return z;
		else
			return gcd(z % e, e);
	}
}
--
// Hill
class hill
{

static void getKeyMatrix(String key, int keyMatrix[][])
{
	int k = 0;
	for (int i = 0; i < 3; i++) 
	{
		for (int j = 0; j < 3; j++) 
		{
			keyMatrix[i][j] = (key.charAt(k)) % 65;
			k++;
		}
	}
}

static void encrypt(int cipherMatrix[][],
			int keyMatrix[][], 
			int messageVector[][])
{
	int x, i, j;
	for (i = 0; i < 3; i++) 
	{
		for (j = 0; j < 1; j++)
		{
			cipherMatrix[i][j] = 0;
		
			for (x = 0; x < 3; x++)
			{
				cipherMatrix[i][j] += 
					keyMatrix[i][x] * messageVector[x][j];
			}
		
			cipherMatrix[i][j] = cipherMatrix[i][j] % 26;
		}
	}
}


static void HillCipher(String message, String key)
{
	int [][]keyMatrix = new int[3][3];
	getKeyMatrix(key, keyMatrix);

	int [][]messageVector = new int[3][1];

	for (int i = 0; i < 3; i++)
		messageVector[i][0] = (message.charAt(i)) % 65;

	int [][]cipherMatrix = new int[3][1];


	encrypt(cipherMatrix, keyMatrix, messageVector);

	String CipherText="";


	for (int i = 0; i < 3; i++)
		CipherText += (char)(cipherMatrix[i][0] + 65);


	System.out.print(" Ciphertext:" + CipherText);
}


public static void main(String[] args) 
{

	String message = "ACT";


	String key = "GYBNQKURP";

	HillCipher(message, key);
	}
}

--
//Caesar Cipher
class CaesarCipher
{

	public static StringBuffer encrypt(String text, int s)
	{
		StringBuffer result= new StringBuffer();

		for (int i=0; i<text.length(); i++)
		{
			if (Character.isUpperCase(text.charAt(i)))
			{
				char ch = (char)(((int)text.charAt(i) +
								s - 65) % 26 + 65);
				result.append(ch);
			}
			else
			{
				char ch = (char)(((int)text.charAt(i) +
								s - 97) % 26 + 97);
				result.append(ch);
			}
		}
		return result;
	}


	public static void main(String[] args)
	{
		String text = "ATTACKATONCE";
		int s = 4;
		System.out.println("Text : " + text);
		System.out.println("Shift : " + s);
		System.out.println("Cipher: " + encrypt(text, s));
	}
}
--
//arp Server:
import java.io.*;
import
java.net.*;
import
java.util.*;
class Serverarp
{
public static void main(String args[])
{
try
{
ServerSocket obj=new
ServerSocket(5604);
Socket obj1=obj.accept();
while(true)
{
DataInputStream din=new DataInputStream(obj1.getInputStream());
DataOutputStream dout=new
DataOutputStream(obj1.getOutputStream()); String str=din.readLine();
String ip[]={"165.165.80.80","165.165.79.1"};
String mac[]={"6A:08:AA:C2","8A:BC:E3:FA"};
for(int i=0;i<ip.length;i++)
{
if(str.equals(ip[i]))
{
dout.writeBytes(mac[i]+'\n');
break;
}
}
obj.close();
}
}
catch(Exception e)
{
System.out.println(e);
}
}
}
--
//arp Client:
import java.io.*;
import java.net.*;
import java.util.*;
class Clientarp
{
public static void main(String args[])
{
try
{
BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
Socket clsct=new Socket("127.0.0.1",5604);
DataInputStream din=new DataInputStream(clsct.getInputStream());
DataOutputStream dout=new
DataOutputStream(clsct.getOutputStream());
System.out.println("Enter the Logical address(IP):");
String str1=in.readLine();
dout.writeBytes(str1+'\n');
String str=din.readLine();
System.out.println("The Physical Address is: "+str);
clsct.close();
}
catch (Exception e)
{
System.out.println(e);
}
}
}
--
//blowfis
import javax.crypto.*;
import java.security.*;
class BlowfishExample {
    public static void main(String[] args) throws Exception {
        String keyString = "mySecretKey";
        String data = "Hello, Blowfish!";

        SecretKeySpec secretKey = new SecretKeySpec(keyString.getBytes(), "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");

        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        System.out.println("Encrypted " + new String(encryptedData));

        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedData = cipher.doFinal(encryptedData);
        System.out.println("Decrypted " + new String(decryptedData));
    }
}
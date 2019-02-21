package br.com.platcorp.uol.util;

import java.net.HttpURLConnection;
import java.net.URL;

public class Rede {

	public static String pegarIP() {
		String ipPagina = null;
		String meuIP = null;
		try {
			URL url = new URL("http://checkip.dyndns.org/");
			HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
			conexao.connect();
			java.io.BufferedReader pagina = new java.io.BufferedReader(
					new java.io.InputStreamReader(conexao.getInputStream()));
			ipPagina = pagina.readLine();
			meuIP = ipPagina.substring(ipPagina.indexOf(": ") + 2, ipPagina.lastIndexOf("</body>"));
			pagina.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return meuIP;
	}

	
}

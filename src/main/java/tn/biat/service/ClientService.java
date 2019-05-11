package tn.biat.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import tn.biat.model.Client;
import tn.biat.model.ResultResearch;
import tn.biat.repository.ClientRepository;
import tn.biat.repository.ResultReasearchRepository;

@Service
@EnableScheduling
public class ClientService {

	@Autowired
	private ResultReasearchRepository resultResearchRepository;

	@Autowired
	private ClientRepository clientRepository;

	public Client create(String firstName, String lastName, String email, String label, String adminManger) {
		return clientRepository.save(new Client(firstName, lastName, email, label, adminManger));
	}

	public List<Client> scrappe() throws IOException {
		stocker();

		List<ResultResearch> listeClients = resultResearchRepository.findAll();
		// String key="AIzaSyCfs0CfrULgHCJZBZ7pxBVejCDgMiYeATs";
		// String key="AIzaSyCrJojffHIgNjrtnpo-Db7J5BGL-91kFz4";
		// String key="AIzaSyCNG6nQad2XWcFEnreKZpL7-Yubo0cEiDM";
		String key = "AIzaSyCTLhjKeF4B1EyR63SId7m6AcHPys4d1e4";
		URL url;
		String googleApi = "";
		for (int i = 0; i < listeClients.size(); i++) {
			ResultResearch Res = listeClients.get(i);
			String qry = Res.getFirstName().concat(" ").concat(Res.getLastName());
			qry = qry.replace(" ", "+");
			url = new URL("https://www.googleapis.com/customsearch/v1?key=" + key
					+ "&cx=013036536707430787589:_pqjad5hr1a&q=" + qry + "&alt=json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				googleApi += output;
				Res.setFlag(true);
				// System.out.println(output);
			}

			// service save f resRech les listes

			Res.setResults(googleApi);

			createResults(Res);
			// System.out.println("Google Api "+googleApi);
			conn.disconnect();
		}

		return clientRepository.findAll();
	}

	// @Scheduled(cron = "* */1 * * * *")
	public void stocker() throws IOException {
		List<Client> listeClient = clientRepository.findAll();

		for (int i = 0; listeClient.size() > i; i++) {

			Client client = listeClient.get(i);

			String id = client.getId();
			String firstName = client.getFirstName();
			String lastName = client.getLastName();

			ResultResearch resultResearch = new ResultResearch();
			resultResearch.setFirstName(firstName);
			resultResearch.setLastName(lastName);
			resultResearch.setResults("");
			resultResearch.setFlag(false);
			createResults(resultResearch);
			List<ResultResearch> listeClientt = resultResearchRepository.findAll();
			List<ResultResearch> rt = listeClientt;

		}

	}

	public void createResults(ResultResearch resultResearch) throws IOException {

		resultResearchRepository.save(resultResearch);
	}

	/*public Client GetByFirstName(String firstName) throws IOException {

		String key = "AIzaSyCrJojffHIgNjrtnpo-Db7J5BGL-91kFz4";
		URL url;
		String googleApi = "";
		Client c = clientRepository.findByFirstName(firstName);
		String qry = c.getLastName();
		qry = qry.replace(" ", "+");
		url = new URL("https://www.googleapis.com/customsearch/v1?key=" + key
				+ "&cx=013036536707430787589:_pqjad5hr1a&q=" + qry + "&alt=json");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		String output;

		while ((output = br.readLine()) != null) {
			googleApi += output;
			System.out.println(output);
		}
		// System.out.println("Google Api "+googleApi);
		conn.disconnect();

		return clientRepository.findByFirstName(firstName);
	}*/
}

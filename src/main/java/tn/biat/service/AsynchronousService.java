package tn.biat.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.biat.config.MyThread;
import tn.biat.model.Client;
import tn.biat.model.ResultResearch;
import tn.biat.repository.ClientRepository;
import tn.biat.repository.ResultReasearchRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AsynchronousService {
	@Autowired
	private ResultReasearchRepository resultResearchRepository;

	@Autowired
	private ClientRepository clientRepository;
    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private ApplicationContext applicationContext;

    public  String param1;
   /* @PersistenceContext
    private EntityManager entityManager;*/

   /* @Async
    @Transactional
    public void printEmployees() {

        List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e").getResultList();
        employees.stream().forEach(e->System.out.println(e.getEmail()));
    }

    @Async("specificTaskExecutor")
    @Transactional
    public void fetchEmployess() {
    
    }*/
	public List<Client> scrappe() throws IOException {
		List<ResultResearch> listeClients = resultResearchRepository.findAll();
System.out.println(listeClients.size());
		
		stocker();
	    

		
		
			String key="AIzaSyAs4ICaN2k_kVm0BQGzqLVtha9Y8roO8EM";
		// String key="AIzaSyCfs0CfrULgHCJZBZ7pxBVejCDgMiYeATs";
		 //String key="AIzaSyCrJojffHIgNjrtnpo-Db7J5BGL-91kFz4";
		 //String key="AIzaSyCNG6nQad2XWcFEnreKZpL7-Yubo0cEiDM";
		//String key = "AIzaSyCTLhjKeF4B1EyR63SId7m6AcHPys4d1e4";
		URL url;
		String googleApi = "";
		for (int i = 0; i < listeClients.size(); i++) {
			
			ResultResearch Res = listeClients.get(i);
			String qry = Res.getFirstName().concat(" ").concat(Res.getLastName());
			qry = qry.replace(" ", "+");
			if(!Res.getFlag()) {
				System.out.println(i);
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
			
		
		
		}
		
		return clientRepository.findAll();
	}

	// @Scheduled(cron = "* */1 * * * *")
	public void stocker() throws IOException {
		List<Client> listeClient = clientRepository.findAll();
		List<ResultResearch> listeClients = resultResearchRepository.findAll();
		if(listeClients.size()<5) {
		for (int i = 0; 5 > i; i++) {

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

		}}

	}
	public void createResults(ResultResearch resultResearch) throws IOException {

		resultResearchRepository.save(resultResearch);
	}

    public void executeAsynchronously() {

        MyThread myThread = applicationContext.getBean(MyThread.class);
        taskExecutor.execute(myThread);
        
    
        }

}
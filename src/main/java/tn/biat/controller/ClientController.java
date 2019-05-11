package tn.biat.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.biat.model.Client;
import tn.biat.service.AsynchronousService;
import tn.biat.service.ClientService;

@RestController
public class ClientController {

	@Autowired
	private ClientService clientService;

	   @Autowired
	    private AsynchronousService anAsynchronousService;
	/*@RequestMapping("/get")
	public Client getClient(@RequestParam String firstName) throws IOException {
		return clientService.GetByFirstName(firstName);

	}*/
	@RequestMapping("/runTask")
    public String executeAsync(@RequestParam String param1) {
		anAsynchronousService.param1 = param1;
        anAsynchronousService.executeAsynchronously();

        return "OK";
    }

}

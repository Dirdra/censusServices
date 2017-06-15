package de.dirdra.censusPlayerServer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.dirdra.census.model.ps2v2.Character;
import de.dirdra.censusPlayerServer.census.CensusAPI;

@Component
@RequestMapping(method=RequestMethod.GET, path="/import")
public class ImportRestController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ImportRestController.class);
	
	@Autowired
	CensusAPI censusAPI;
	
	@RequestMapping(method=RequestMethod.GET, path="/character")
	public ResponseEntity<Character> importCharacterById(@RequestParam(name="id") final String id) {
		LOG.debug("requestParam id > {}", id);
		
		final Character character = censusAPI.getCharacter(id);
		
		final ResponseEntity<Character> result = new ResponseEntity<Character>(character, HttpStatus.OK);
		return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/character/{id}")
	public ResponseEntity<Character> importCharacterByUrlID(@PathVariable("id") final String id) {
		LOG.debug("pathVar-id > {}", id);
		
		final Character character = censusAPI.getCharacter(id);
		
		final ResponseEntity<Character> result = new ResponseEntity<Character>(character, HttpStatus.OK);
		return result;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/{id}")
	public ResponseEntity<Boolean> importCharacterToDB(@PathVariable("id") final String id) {
		LOG.debug("pathVar-id > {}", id);
		
		final boolean imported = censusAPI.importCharacter(id);
		
		return new ResponseEntity<Boolean>(imported, HttpStatus.OK);
	}
}

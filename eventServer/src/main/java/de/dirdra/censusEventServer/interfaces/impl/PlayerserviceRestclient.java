package de.dirdra.censusEventServer.interfaces.impl;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.dirdra.census.model.ps2v2.Character;

@FeignClient(value = "playerServer", name = "playerServer", fallback = PlayerserviceRestclientFallback.class)
public interface PlayerserviceRestclient {
	@RequestMapping(method=RequestMethod.GET, path="/import/character/{id}")
	Character getCharacterById(@PathVariable("id") final String id);
	
	@RequestMapping(method = RequestMethod.GET, path="/characters/{id}")
	ResponseEntity<de.dirdra.censusPlayerServer.jpa.repository.model.Character> getPersistentChar(@PathVariable("id") final String id);
	
	@RequestMapping(method = RequestMethod.GET, path="/import/{id}")
	ResponseEntity<de.dirdra.censusPlayerServer.jpa.repository.model.Character> importCharToDB(@PathVariable("id") final String id);
}
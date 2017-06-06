package de.dirdra.censusEventServer.interfaces.impl;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.dirdra.census.model.ps2v2.Character;

@FeignClient(value = "playerServer", name = "playerServer")
public interface PlayerserviceRestclient {
	@RequestMapping(method=RequestMethod.GET, path="/import/character/{id}")
	Character getCharacterById(@PathVariable("id") final String id);
}

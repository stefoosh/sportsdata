package sh.stefoosh.sportsdata.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sh.stefoosh.sportsdata.model.Countries;
import sh.stefoosh.sportsdata.model.States;
import sh.stefoosh.sportsdata.repository.CountriesRepository;
import sh.stefoosh.sportsdata.repository.StatesRepository;

import java.util.List;

import static sh.stefoosh.sportsdata.constants.Endpoint.APPLICATION_JSON;
import static sh.stefoosh.sportsdata.constants.Endpoint.COUNTRIES;
import static sh.stefoosh.sportsdata.constants.Endpoint.STATES;

@RestController
@RequestMapping("regional")
public final class RegionalController {

    @Autowired
    private CountriesRepository countriesRepository;

    @Autowired
    private StatesRepository statesRepository;

    private RegionalController() {
    }

    @GetMapping(path = COUNTRIES, produces = APPLICATION_JSON)
    public List<Countries> getCountries() {
        return countriesRepository.findAll();
    }

    @GetMapping(path = STATES, produces = APPLICATION_JSON)
    public List<States> getStates() {
        return statesRepository.findAll();
    }
}

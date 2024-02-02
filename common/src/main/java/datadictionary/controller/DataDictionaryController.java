package datadictionary.controller;

import datadictionary.DataDictionaryResponse;
import datadictionary.application.DataDictionaryAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("data-dictionaries")
public class DataDictionaryController {

    private final DataDictionaryAppService appService;

    @GetMapping
    public List<DataDictionaryResponse> query() {
        return appService.query();
    }

}

package fr.cedricsevestre.controller.rest;

import java.util.Locale;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.cedricsevestre.conf.SerializableResourceBundleMessageSource;

@Controller
@RequestMapping("/@front/labels/{lang}")
public class SerializableMessageBundleController {

    @Autowired
    SerializableResourceBundleMessageSource messageBundle;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Properties list(@PathVariable(value = "lang") String lang) {
        return messageBundle.getAllProperties(new Locale(lang));
    }
}
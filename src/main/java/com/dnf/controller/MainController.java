package com.dnf.controller;

import com.dnf.model.dto.item.ItemListDTO;
import com.dnf.model.dto.item.ItemRowsDTO;
import com.dnf.model.dto.itemDetail.ItemDetailDTO;
import com.dnf.model.dto.server.ServerRowsDTO;
import com.google.gson.Gson;
import kong.unirest.HttpResponse;
import kong.unirest.HttpStatus;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class MainController {

    private static final String API_KEY = "5zpiaHcczxfMFje4YkfJYr6ai8wB5Fpi";

    Gson gson = new Gson();

    @RequestMapping("/")
    public String main() {
        return "/index";
    }

    @RequestMapping("/servers")
    public String servers(Model model) {
        String serverListUrl = "https://api.neople.co.kr/df/servers";
        HttpResponse<String> response = Unirest.get(serverListUrl)
                .queryString("apikey", API_KEY)
                .asString();
        if (response.getStatus() != HttpStatus.OK) {
            return "/";
        }
        ServerRowsDTO serverRowsDTO = gson.fromJson(response.getBody(), ServerRowsDTO.class);
        model.addAttribute("serverList", serverRowsDTO.getRows());
        return "/server_list";
    }

    @GetMapping("/itemSearch")
    public String itemSearch() {
        return "/item";
    }

    @PostMapping("/itemSearch")
    public String itemSearch(@RequestParam(value = "keyword") String keyword, @RequestParam(value = "wordType", required = false, defaultValue = "front") String wordType, Model model) {
        log.info("itemSearch");
        log.info(keyword);
        log.info(wordType);
        String itemSearchUrl = "https://api.neople.co.kr/df/items";
        log.info(itemSearchUrl);
        HttpResponse<String> response = Unirest.get(itemSearchUrl)
                .queryString("itemName", keyword)
                .queryString("wordType", wordType)
                .queryString("limit", 30)
                .queryString("apikey", API_KEY)
                .asString();

        if (response.getStatus() != HttpStatus.OK) {
            return "/";
        }
        log.info(response.getBody());

        ItemRowsDTO itemRowsDTO = gson.fromJson(response.getBody(), ItemRowsDTO.class);
        log.info(itemRowsDTO.toString());

        List<ItemListDTO> itemList = itemRowsDTO.getRows();

        model.addAttribute("keyword", keyword);
        model.addAttribute("wordType", wordType);
        if (!itemList.isEmpty()) {
            model.addAttribute("itemList", itemList);
        }
        return "/item";
    }

    @GetMapping("itemInfo")
    public String itemInfo(@RequestParam("itemId") String itemId,
                           @RequestParam("keyword") String keyword,
                           @RequestParam("wordType") String wordType,
                           Model model) throws UnsupportedEncodingException {
        log.info("itemInfo");
        keyword = URLDecoder.decode(keyword, "UTF-8");
        log.info(itemId);

        String itemDetailUrl = "https://api.neople.co.kr/df/items/" + itemId;

        HttpResponse<String> response = Unirest.get(itemDetailUrl)
                .queryString("apikey", API_KEY)
                .asString();
        if (response.getStatus() != HttpStatus.OK) {
            return "/";
        }

        ItemDetailDTO itemDetailDTO = gson.fromJson(response.getBody(), ItemDetailDTO.class);

        log.info(itemDetailDTO.toString());

        model.addAttribute("itemDetail", itemDetailDTO);
        model.addAttribute("keyword", keyword);
        model.addAttribute("wordType", wordType);

        return "item-detail";
    }


}

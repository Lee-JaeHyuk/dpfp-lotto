package com.dpfp.lottogenerator.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LottoService {

    private static final String LOTTO_URL = "https://www.dhlottery.co.kr/gameResult.do?method=byWin";

    public List<Integer> getRecentLottoNumbers() throws IOException {
        String url = LOTTO_URL;
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select("div.win_result > div > span");
        List<Integer> numbers = new ArrayList<>();
        for (Element element : elements) {
            numbers.add(Integer.parseInt(element.text()));
        }
        return numbers;
    }

    public int getRecentLottoWinners() throws IOException {
        String url = LOTTO_URL;
        Document doc = Jsoup.connect(url).get();
        Element element = doc.selectFirst("div.tbl_data.tbl_data_col tbody tr:first-child td:nth-child(4)");
        return Integer.parseInt(element.text().replace(",", ""));
    }



}

package com.dpfp.lottogenerator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class lottotest {

    @Test
    public void testGetRecentLottoNumbers() throws IOException {
        String LOTTO_URL = "https://www.dhlottery.co.kr/gameResult.do?method=byWin";
        String url = LOTTO_URL;
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select("div.win_result > div > span");
        List<Integer> numbers = new ArrayList<>();
        for (Element element : elements) {
            numbers.add(Integer.parseInt(element.text()));
        }
        System.out.println(numbers);

    }
    @Test
    public void getNextEpisodeBasedonDate() throws ParseException, IOException {
        Document doc = Jsoup.connect("https://m.dhlottery.co.kr/qr.do?method=winQr&v=0809q021825303444q050812313445q060817202240q121623253641q0408092329440000002233").get();
        String contents = doc.select("div.winner_number h3 span").text();
        String drwNo = contents.substring(1,4)+"회";
        String Year = contents.substring(6,10)+"-";
        String Month = contents.substring(11,13)+"-";
        String Day = contents.substring(14,16);
        String drwDate = Year+Month+Day;
        System.out.println(drwNo);	// 회차
        System.out.println(drwDate);	//추첨일
        String content= doc.select("div.list span").text();
        int drwtNo1 = Integer.parseInt(content.split("\\s")[0]);
        int drwtNo2 = Integer.parseInt(content.split("\\s")[1]);
        int drwtNo3 = Integer.parseInt(content.split("\\s")[2]);
        int drwtNo4 = Integer.parseInt(content.split("\\s")[3]);
        int drwtNo5 = Integer.parseInt(content.split("\\s")[4]);
        int drwtNo6 = Integer.parseInt(content.split("\\s")[5]);
        int bnusNo = Integer.parseInt(content.split("\\s")[6]);
        System.out.println(drwtNo1+","+drwtNo2+","+drwtNo3+","+drwtNo4+","+drwtNo5+","+drwtNo6+","+bnusNo);	//당첨번호 + 보너스


    }



}

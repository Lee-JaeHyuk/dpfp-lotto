package com.dpfp.lottogenerator.controller;

import com.dpfp.lottogenerator.service.LottoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class LottoController {

    private LottoService lottoService;
    // test

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
    /*
    @GetMapping("/")
    public String index(Model model) throws IOException {
        model.addAttribute("numbers", lottoService.getRecentLottoNumbers());
        model.addAttribute("winners", lottoService.getRecentLottoWinners());
        return "index";
    }*/

    @PostMapping("/fortune")
    public String getFortune(@RequestParam("birthDate") String birthDate, Model model) {
        // 생년월일 문자열을 LocalDate 객체로 변환
        LocalDate birthdateObj = LocalDate.parse(birthDate);

        // 생년월일로부터 오늘까지의 일 수 계산
        long days = ChronoUnit.DAYS.between(birthdateObj, LocalDate.now());

        // 오늘까지의 일 수를 기반으로 운세 계산
        String fortune = getFortuneByDays(days);

        // 결과를 모델에 저장
        model.addAttribute("birthDate", birthDate);
        model.addAttribute("fortune", fortune);

        // 결과를 보여줄 페이지 이름 리턴
        return "result";
    }

    @PostMapping("/generate")
    public String generateLottoNumbers(@RequestParam(name = "count") int count, @RequestParam(name = "fortune") String fortune , Model model) {
        List<List<Integer>> numbers = new ArrayList<>();
        Random random = new Random();

        model.addAttribute("fortune", fortune);

        // 랜덤함수를 통해 중복을 제거하며 숫자6개 생성
        for (int i = 0; i < count; i++) {
            List<Integer> numberList = new ArrayList<>();
            while (numberList.size() < 6) {
                int number = random.nextInt(45) + 1;
                if (!numberList.contains(number)) {
                    numberList.add(number);
                }
            }
            numbers.add(numberList);
        }

        model.addAttribute("numbers", numbers);


        return "result";
    }

    private String getFortuneByDays(long days) {
        // 오늘까지의 일 수를 이용하여 운세를 계산
        int num = new Random(days).nextInt(5);
        // 시드값으로 입력한 사용자의 고유 일수 입력
        switch (num) {
            case 0:
                return "대길입니다. 5천원이요~";
            case 1:
                return "길입니다. 4장 정도 추천합니다.";
            case 2:
                return "보통입니다. 3천원!";
            case 3:
                return "흉이네요. 이럴땐 다음에 사시는 것도..";
            case 4:
                return "대흉입니다. 오히려 5장 갑시다!!";
            default:
                return "운세를 계산할 수 없습니다. 당신은 이미 당첨이야";
        }
    }
}




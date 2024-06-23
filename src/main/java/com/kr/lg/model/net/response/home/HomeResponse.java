package com.kr.lg.model.net.response.home;

import com.kr.lg.model.common.root.DefaultResponse;
import com.kr.lg.model.common.layer.MainBLayer;
import com.kr.lg.model.common.layer.MainTLayer;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class HomeResponse extends DefaultResponse { // HomeResponse

    List<MainTLayer> trials;
    List<MainBLayer> top;
    List<MainBLayer> jungle;
    List<MainBLayer> mid;
    List<MainBLayer> bot;
    List<MainBLayer> spt;

    public HomeResponse(List<MainTLayer> trials, List<MainBLayer> boards) {
        this.trials = trials;
        this.top = new ArrayList<>();
        this.jungle = new ArrayList<>();
        this.mid = new ArrayList<>();
        this.bot = new ArrayList<>();
        this.spt = new ArrayList<>();
        boards.stream().forEach(it -> {
            switch (it.getLineType()) {
                case TOP: top.add(it); break;
                case JUNGLE: jungle.add(it); break;
                case MID: mid.add(it); break;
                case ADD: bot.add(it); break;
                case SPT: spt.add(it); break;
            }
        });
    }
}

package com.zepp.rpp.bootstrap;

import com.zepp.rpp.domains.Notice;
import com.zepp.rpp.domains.Travels;
import com.zepp.rpp.domains.User;
import com.zepp.rpp.repositories.TravelsRepository;
import com.zepp.rpp.repositories.UserRepository;
import com.zepp.rpp.repositories.notice.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class RppBootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private NoticeRepository noticeRepository;
    private TravelsRepository travelsRepository;
    private UserRepository userRepository;

    @Autowired
    public RppBootstrap(NoticeRepository noticeRepository, TravelsRepository travelsRepository, UserRepository userRepository) {
        this.noticeRepository = noticeRepository;
        this.travelsRepository = travelsRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        noticeRepository.saveAll(getNotices());
        //travelsRepository.saveAll(getTravels());
        log.debug("Loading start data");
    }

    private List<Notice> getNotices(){
        List<Notice> notices = new ArrayList<Notice>();

        Notice n1 = new Notice();
        n1.setCreateDate(new Date());
        n1.setNoticeTitle("Putovanja u 2019-oj");
        n1.setNoticeNote("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis tristique dignissim congue. Donec ac ligula et sem consectetur tincidunt. Integer ac faucibus eros. Vestibulum ex odio, finibus quis venenatis vitae, ultrices in turpis. Nullam ut congue leo. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum malesuada diam sit amet urna dignissim condimentum. Mauris convallis, tellus rutrum ultrices finibus, turpis eros dignissim urna, vel congue est lectus a metus. In hac habitasse platea dictumst. Fusce quis odio eu lectus cursus efficitur ac nec nulla. Ut sodales gravida purus, a maximus ipsum varius non. In facilisis tortor purus, nec finibus mauris porttitor vel. Ut vulputate libero condimentum mauris consectetur lacinia. Suspendisse quam diam, facilisis et lacus ut, malesuada molestie nulla. Praesent laoreet lectus est, in cursus velit molestie eu. Donec in tempor magna, id bibendum dolor.");

        Notice n2 = new Notice();
        n2.setCreateDate(new Date());
        n2.setNoticeTitle("Obilasci Evropskih metropola maj 2019");
        n2.setNoticeNote("Phasellus non lectus nunc. Integer euismod magna vel urna venenatis, ac malesuada nisi consequat. Phasellus sed justo lacinia, pulvinar magna nec, fermentum ipsum. Duis sed dolor mauris. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Quisque neque leo, lobortis vel dapibus eget, feugiat quis magna. Nullam mauris enim, pellentesque sed bibendum eget, molestie nec ante. Donec eget metus ac arcu convallis euismod a eget ipsum. Nam maximus purus eu tellus laoreet molestie. Curabitur faucibus vel nisi at tincidunt. Nam interdum nibh vitae pellentesque posuere. Etiam a dignissim tellus, at mollis elit. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Cras hendrerit varius velit ac bibendum.");

        Notice n3 = new Notice();
        n3.setCreateDate(new Date());
        n3.setNoticeTitle("Avionski prevoz");
        n3.setNoticeNote("Nunc neque diam, viverra nec dapibus sed, bibendum quis nulla. Maecenas feugiat ipsum ut orci pulvinar fringilla. Donec tincidunt nulla vitae massa molestie tempus. Donec erat velit, pellentesque sed pellentesque in, congue sit amet dui. Fusce non mi massa. Sed mattis nunc at enim interdum, eu viverra dolor consequat. Nunc diam purus, placerat ut mollis sit amet, maximus eget dolor.");

        Notice n4 = new Notice();
        n4.setCreateDate(new Date());
        n4.setNoticeTitle("Prekookenska krstarenja");
        n4.setNoticeNote("Nunc neque diam, viverra nec dapibus sed, bibendum quis nulla. Maecenas feugiat ipsum ut orci pulvinar fringilla. Donec tincidunt nulla vitae massa molestie tempus. Donec erat velit, pellentesque sed pellentesque in, congue sit amet dui. Fusce non mi massa. Sed mattis nunc at enim interdum, eu viverra dolor consequat. Nunc diam purus, placerat ut mollis sit amet, maximus eget dolor.");

        Notice n5 = new Notice();
        n5.setCreateDate(new Date());
        n5.setNoticeTitle("First minute ponude");
        n5.setNoticeNote("Donec condimentum augue vel neque posuere efficitur. Donec enim justo, pulvinar eu sagittis nec, sagittis maximus massa. Sed sed rutrum augue. Praesent vitae ante ac sem luctus aliquet vel vel orci. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. In semper viverra blandit. Sed sit amet lectus sit amet ligula vehicula facilisis.");

        Notice n6 = new Notice();
        n6.setCreateDate(new Date());
        n6.setNoticeTitle("Pogledajte statistiku");
        n6.setNoticeNote("Phasellus non lectus nunc. Integer euismod magna vel urna venenatis, ac malesuada nisi consequat. Phasellus sed justo lacinia, pulvinar magna nec, fermentum ipsum. Duis sed dolor mauris. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Quisque neque leo, lobortis vel dapibus eget, feugiat quis magna.");


        notices.add(n1);
        notices.add(n2);
        notices.add(n3);
        notices.add(n4);
        notices.add(n5);
        notices.add(n6);

        return notices;
    }

    /*private List<Travels> getTravels(){
        List<Travels> travelsList = new ArrayList<>();
        User user = userRepository.findByUsername("dare");
        Travels t1 = new Travels();
        t1.setPeriod(201511);
        t1.setPassenger_type("Overseas visitor");
        t1.setDirection("Arrival");
        t1.setCountry("Germany");
        t1.setCounter(191);
        t1.setUser(user);
        travelsList.add(t1);

        Travels t2 = new Travels();
        t2.setPeriod(201512);
        t2.setPassenger_type("Overseas visitor");
        t2.setDirection("Arrival");
        t2.setCountry("Germany");
        t2.setCounter(191);
        t2.setUser(user);
        travelsList.add(t2);

        return travelsList;
    }*/
}

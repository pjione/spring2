package com.spring.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Board {
    private Integer bno;
    private String title;
    private String content;
    private String writer;
    private int view_cnt;
    private int comment_cnt;
    private Date reg_date;
    private Date up_date;
    public Board() { this("","",""); }
    public Board(String title, String content, String writer){
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}

package gui.component.buttons;

import gui.CustomStyle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//빨간 선의 빨간 글씨를 가진 버튼
//삭제 등 위험한 액션에 주는 버튼으로 이 버튼을 사용하면 경고창을 띄워줄 것을 권고
//active 되면 경고창이므로 press 액션 스타일은 생략
public class DangerButton extends RoundButtons{
    public  DangerButton(String buttonLabel){
        super(buttonLabel);

        //기본 색 셋팅
        roundButtonContent.setBorder(new CustomStyle.DrawRoundSquare(8,new Color(232, 19, 19), CustomStyle.white));
        roundButtonLabel.setForeground(new Color(232, 19, 19));

        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
                roundButtonContent.setBorder(new CustomStyle.DrawRoundSquare(8,new Color(232, 19, 19), CustomStyle.white));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                roundButtonContent.setBorder(new CustomStyle.DrawRoundSquare(8,new Color(232, 19, 19), new Color(232, 19, 19, 30)));
            }

            @Override
            public void mouseClicked(MouseEvent e) {}
        });
    }
}

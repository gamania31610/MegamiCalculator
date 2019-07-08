package com.example.megamicalculator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar sb_NowStar,sb_TargetStar,sb_Price;
    private TextView tv_NowStar,tv_Target, MainTextview;
    private EditText ed_MemoPiece,ed_NowStarNum,ed_TargetStarNum,ed_Price;
    private Button bt_Count;
    private Spinner sp_Weapon,sp_Unlock;
    private int[] pig= {30,100,120,150};
    private int[] unlockMemo= {0,15,45,145};
    private int[] weaponMemo={0,50,10,10,10,10,10};
    private int now_star,now_piece, target_star,now_price,need_memo=0,need_pig=0;
    private boolean check1,check2,check3,check4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_main);


        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        final String[] weapon={getString(R.string.no),"30","50","70","90","110","130"};
        final String[] unlock={getString(R.string.already),getString(R.string.star1),getString(R.string.star2),getString(R.string.star3)};

        sb_NowStar = (SeekBar) findViewById(R.id.sb_NowStar);
        sb_TargetStar = (SeekBar) findViewById(R.id.sb_TargetStar);
        sb_Price = (SeekBar) findViewById(R.id.sb_Price);

        tv_NowStar = (TextView) findViewById(R.id.tv_NowStar);;
        tv_Target = (TextView) findViewById(R.id.tv_TargetStar);;

        ed_NowStarNum = (EditText) findViewById(R.id.ed_NowStarNum);;
        ed_TargetStarNum = (EditText) findViewById(R.id.ed_TargetStarNum);;
        ed_Price = (EditText) findViewById(R.id.ed_Price);;
        ed_MemoPiece = (EditText) findViewById(R.id.ed_MemoPiece);

        MainTextview = (TextView) findViewById(R.id.MaintextView);
        bt_Count = (Button) findViewById (R.id.bt_Count);

        sp_Weapon = (Spinner) findViewById(R.id.sp_Weapon);
        sp_Unlock = (Spinner) findViewById(R.id.sp_Unlock);

        ArrayAdapter<String> weaponLV = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                weapon);
        sp_Weapon.setAdapter(weaponLV);

        ArrayAdapter<String> unlockChar = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                unlock);
        sp_Unlock.setAdapter(unlockChar);
        sp_Unlock.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(sp_Unlock.getSelectedItemPosition()>0){
                    sb_NowStar.setProgress(sp_Unlock.getSelectedItemPosition());
                    ed_NowStarNum.setText(String.valueOf(sp_Unlock.getSelectedItemPosition()));
                    sb_NowStar.setEnabled(false);
                    ed_NowStarNum.setEnabled(false);
                }
                else {
                    sb_NowStar.setEnabled(true);
                    ed_NowStarNum.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });


        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

        ed_NowStarNum.setText("1");
        ed_TargetStarNum.setText("5");
        ed_Price.setText("1");
        ed_MemoPiece.setText("0");

        MainTextview.setMovementMethod(new ScrollingMovementMethod());
        MainTextview.setText(R.string.welcome);


        ed_NowStarNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    int i = Integer.parseInt(s.toString());
                    if (i >= 1 && i <= 5) {
                        sb_NowStar.setProgress(i - 1);
                    }
                }
                catch (Exception e){}
            }
        });

        ed_TargetStarNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    int i = Integer.parseInt(s.toString());
                    if (i >= 1 && i <= 5) {
                        sb_TargetStar.setProgress(i - 1);
                    }
                }
                catch (Exception e){}
            }
        });

        ed_Price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    int i = Integer.parseInt(s.toString());
                    if (i >= 1 && i <= 5) {
                        sb_Price.setProgress(i - 1);
                    }
                }
                catch (Exception e){}
            }
        });

        sb_NowStar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ed_NowStarNum.setText(String.valueOf(progress+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        sb_TargetStar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ed_TargetStarNum.setText(String.valueOf(i+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sb_Price.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ed_Price.setText(String.valueOf(i+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        bt_Count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imm.hideSoftInputFromWindow(findViewById(R.id.bt_Count).getWindowToken(), 0);
                MainTextview.setText("");

                need_memo=0;
                need_pig=0;

                now_star=Integer.parseInt(ed_NowStarNum.getText().toString());
                target_star =Integer.parseInt(ed_TargetStarNum.getText().toString());
                now_price=Integer.parseInt(ed_Price.getText().toString());

                if(ed_MemoPiece.getText().toString().equals("")){
                    now_piece=0;
                }
                else {
                    now_piece=Integer.parseInt(ed_MemoPiece.getText().toString());
                }

                check1=CheckNowStar(now_star);
                check2=CheckTargetStar(now_star,target_star);
                check3=CheckNowPiece(now_star,now_piece,sp_Unlock.getSelectedItemPosition());
                check4=CheckNowPrice(now_price);


                if(check1==true && check2==true && check3==true && check4==true && sp_Unlock.getSelectedItemPosition()==0) {
                    need_memo=0;
                    for (int i = target_star - 2; i >= now_star - 1; i--) {
                        need_memo += pig[i];
                    }

                    for (int i = sp_Weapon.getSelectedItemPosition(); i >= 0 ; i--) {
                        need_memo+=weaponMemo[i];
                    }

                    need_memo-=now_piece;

                    if(need_memo>0) {
                        MainTextview.append("\n"+getString(R.string.lack1)+need_memo+getString(R.string.lack2));
                        while(need_memo>20 && now_price!=5) {
                            need_pig+=20*now_price;
                            need_memo-=20;
                            now_price++;
                        }
                        need_pig+=need_memo*now_price;
                        MainTextview.append("\n" + getString(R.string.need)+ need_pig + getString(R.string.pig));
                    }
                    else {
                        MainTextview.append("\n"+getString(R.string.enough));
                    }
                }
                else if(check1==true && check2==true && check3==true && check4==true && sp_Unlock.getSelectedItemPosition()!=0){//資料正確，且須解放
                    need_memo=0;
                    if(now_piece>=unlockMemo[sp_Unlock.getSelectedItemPosition()]) {//碎片足以解放腳色
                        now_piece-=unlockMemo[sp_Unlock.getSelectedItemPosition()];//扣掉解放所需碎片
                        for (int i = target_star - 2; i >= now_star - 1; i--) {
                            need_memo += pig[i];//計算所需碎片
                        }

                        for (int i = sp_Weapon.getSelectedItemPosition(); i >= 0; i--) {
                            need_memo += weaponMemo[i];//計算專武所需碎片
                        }

                        need_memo -= now_piece;//扣掉現有碎片

                        if (need_memo > 0) {
                            int memo=need_memo;
                            while (need_memo > 20 && now_price != 5) {
                                need_pig += 20 * now_price;
                                need_memo -= 20;
                                now_price++;//計算每片碎片價格為1~4所需的母豬量
                            }
                            need_pig += need_memo * now_price;//計算價格為5的母豬量
                            MainTextview.append(getString(R.string.need1) + unlockMemo[sp_Unlock.getSelectedItemPosition()]
                                    + getString(R.string.need2)+memo+getString(R.string.need3)+need_pig + getString(R.string.need4));//如果需要母豬
                        } else {
                            MainTextview.append("\n"+getString(R.string.piece_enough));//碎片量足夠
                        }
                    }
                    else {
                        MainTextview.append("\n"+getString(R.string.unlock1)+unlockMemo[sp_Unlock.getSelectedItemPosition()]+getString(R.string.unlock2)
                                +(unlockMemo[sp_Unlock.getSelectedItemPosition()]-now_piece)+getString(R.string.unlock3));
                    }
                }
            }
        });

    }
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // TODO Auto-generated method stub
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null
                    && getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.onTouchEvent(event);
    }

    public boolean CheckNowStar(int now_star){
        if (now_star<1)
        {
            MainTextview.append(getString(R.string.now_star)+getString(R.string.less_or_equal0)+"\n\n");
            return false;
        }
        else if (now_star>5)
        {
            MainTextview.append(getString(R.string.now_star)+getString(R.string.more5)+"\n\n");
            return false;
        }
        else {
            return true;
        }
    }

    public boolean CheckTargetStar(int now_star,int TargetStar){
        if (TargetStar<now_star){
            MainTextview.append(getString(R.string.target_star)+getString(R.string.dont_less)+getString(R.string.now_star)+getString(R.string.dont_less2)+"\n\n");
            return false;
        }
        else if (TargetStar>5){
            MainTextview.append(getString(R.string.target_star)+getString(R.string.more5)+"\n\n");
            return false;
        }
        else if (TargetStar<=1){
            MainTextview.append(getString(R.string.target_star)+getString(R.string.less_or_equal1)+"\n\n");
            return false;
        }
        else if(TargetStar<=5 && TargetStar>1 && now_star == TargetStar){
            MainTextview.append(getString(R.string.same)+"\n\n");
            return false;
        }
        else {
            return true;
        }
    }

    public boolean CheckNowPiece(int now_star,int now_piece,int unlock){
        if (now_piece<0){
            MainTextview.append(getString(R.string.piece_num)+getString(R.string.less0)+"\n\n");
            return false;
        }
        else if (unlock!=0 && now_piece>=515){
            MainTextview.append(getString(R.string.piece515));
            return false;
        }
        else if (unlock==0 && now_star==1 && now_piece>=500){
            MainTextview.append(getString(R.string.piece_enoughMAX));
            return false;
        }
        else if (unlock==0 && now_star==2 && now_piece>=470){
            MainTextview.append(getString(R.string.piece_enoughMAX));
            return false;
        }
        else if (unlock==0 && now_star==3 && now_piece>=370){
            MainTextview.append(getString(R.string.piece_enoughMAX));
            return false;
        }
        else {
            return true;
        }
    }

    public boolean CheckNowPrice(int now_price){
        if (now_price<1){
            MainTextview.append(getString(R.string.memo_price)+getString(R.string.less1)+"\n\n");
            return false;
        }
        else if (now_price>5){
            MainTextview.append(getString(R.string.memo_price)+getString(R.string.more5)+"\n\n");
            return false;
        }
        else {
            return true;
        }
    }
}



package com.example.game2048;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class DataGame{
    private static DataGame dataGame;
    private ArrayList<Integer> arrNum = new ArrayList<>();
    private int[] ArrayColor;
    private int[][] Array2C = new int[4][4];
    private Random random = new Random();
    private int score = 0;

    static {
        dataGame = new DataGame();
    }

    public static DataGame getDataGame() {
        return dataGame;
    }

    public void init(Context context) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Array2C[i][j] = 0;
                arrNum.add(0);
            }
        }
        score = 0;
        TypedArray typedArray = context.getResources().obtainTypedArray(R.array.colorNumItem);
        ArrayColor = new int[typedArray.length()];
        for (int i = 0; i < typedArray.length(); i++) {
            ArrayColor[i] = typedArray.getColor(i, 0);
        }
        typedArray.recycle();
        randomNumber();
        chuyenDoi();
    }

    public ArrayList<Integer> getArrNum() {
        return arrNum;
    }

    public int ColorNum(int num) {
        if (num == 0) {
            return Color.WHITE;
        } else {
            int a = (int) (Math.log(num) / Math.log(2));
            return ArrayColor[a - 1];
        }
    }

    public void randomNumber() {
        int num0 = 0;
        for (int i = 0; i < 16; i++) {
            if (arrNum.get(i) == 0) {
                num0++;
            }
        }
        int num0Random;
        if (num0 > 1) {
            num0Random = random.nextInt(2) + 1;
        } else {
            if (num0 == 1) {
                num0Random = 1;
            } else {
                num0Random = 0;
            }
        }
        while (num0Random != 0) {
            int i = random.nextInt(4), j = random.nextInt(4);
            if (Array2C[i][j] == 0) {
                Array2C[i][j] = 2;
                num0Random--;
            }
        }
    }

    public void chuyenDoi() {
        arrNum.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arrNum.add(Array2C[i][j]);
            }
        }
    }
    public int getScore(){
        return score;
    }

    public void slideLeft() {
        boolean check = false;
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 4 ; j++) {
                int numit = Array2C[i][j];
                if (numit == 0) {
                    continue;
                } else {
                    for (int k = j + 1; k < 4; k++) {
                        int nextnumit = Array2C[i][k];
                        if (nextnumit == 0) {
                            continue;
                        } else {
                            if (nextnumit == numit) {
                                Array2C[i][j] *= 2;
                                Array2C[i][k] = 0;
                                score += Array2C[i][j];
                                check = true;
                                break;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int numit = Array2C[i][j];
                if (numit == 0) {
                    for (int k = j + 1; k < 4 ; k++) {
                        int nextnumit = Array2C[i][k];
                        if (nextnumit == 0) {
                            continue;
                        } else {
                            Array2C[i][j] = Array2C[i][k];
                            Array2C[i][k] = 0;
                            check = true;
                            break;
                        }
                    }
                }
            }
        }
        if (check == true) randomNumber();
        chuyenDoi();
    }



    public void slideRight() {
        boolean check = false;
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                int numit = Array2C[i][j];
                if (numit == 0) {
                    continue;
                } else {
                    for (int k = j - 1; k >= 0; k--) {
                        int nextnumit = Array2C[i][k];
                        if (nextnumit == 0) {
                            continue;
                        } else {
                            if (nextnumit == numit) {
                                Array2C[i][j] *= 2;
                                Array2C[i][k] = 0;
                                score += Array2C[i][j];
                                check = true;
                                break;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                int numit = Array2C[i][j];
                if (numit == 0) {
                    for (int k = j - 1; k >= 0; k--) {
                        int nextnumit = Array2C[i][k];
                        if (nextnumit == 0) {
                            continue;
                        } else {
                            Array2C[i][j] = Array2C[i][k];
                            Array2C[i][k] = 0;
                            check = true;
                            break;
                        }
                    }
                }
            }
        }
        if (check == true) randomNumber();
        chuyenDoi();
    }



    public void slideDown() {
        boolean check = false;
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 4 ; j++) {
                int numit = Array2C[j][i];
                if (numit == 0) {
                    continue;
                } else {
                    for (int k = j + 1; k < 4; k++) {
                        int nextnumit = Array2C[k][i];
                        if (nextnumit == 0) {
                            continue;
                        } else {
                            if (nextnumit == numit) {
                                Array2C[j][i] *= 2;
                                Array2C[k][i] = 0;
                                score += Array2C[j][i];
                                check = true;
                                break;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int numit = Array2C[j][i];
                if (numit == 0) {
                    for (int k = j + 1; k < 4 ; k++) {
                        int nextnumit = Array2C[k][i];
                        if (nextnumit == 0) {
                            continue;
                        } else {
                            Array2C[j][i] = Array2C[k][i];
                            Array2C[k][i] = 0;
                            check = true;
                            break;
                        }
                    }
                }
            }
        }
        if (check == true) randomNumber();
        chuyenDoi();
    }



    public void slideUp() {
        boolean check = false;
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                int numit = Array2C[j][i];
                if (numit == 0) {
                    continue;
                } else {
                    for (int k = j - 1; k >= 0; k--) {
                        int nextnumit = Array2C[k][i];
                        if (nextnumit == 0) {
                            continue;
                        } else {
                            if (nextnumit == numit) {
                                Array2C[j][i] *= 2;
                                Array2C[k][i] = 0;
                                score += Array2C[j][i];
                                check = true;
                                break;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                int numit = Array2C[j][i];
                if (numit == 0) {
                    for (int k = j - 1; k >= 0; k--) {
                        int nextnumit = Array2C[k][i];
                        if (nextnumit == 0) {
                            continue;
                        } else {
                            Array2C[j][i] = Array2C[k][i];
                            Array2C[k][i] = 0;
                            check = true;
                            break;
                        }
                    }
                }
            }
        }
        if (check == true) randomNumber();
        chuyenDoi();
    }
}

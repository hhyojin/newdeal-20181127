package q181214;

public class Anagram {

  private static int lenght;

  public boolean checkAnagram(String first, String second) {

    boolean textCheck = false; 
    //길이 같은지 확인
    boolean lenghtCheck = (first.length() == second.length())?true:false;

    //길이가 같으면 char 배열로 만듦
    if(lenghtCheck) {
      lenght = first.length();
      char[] firstList = sort(first);
      char[] secondList = sort(second);
      
      //배열의 문자 비교
      for(int i=0; i<lenght;i++){
        if(firstList[i] == secondList[i]) {
          textCheck = true;
        } else {
          textCheck = false;
          break;
        }
      }
    } 
    return textCheck;   
  }
  
  //배열 생성 및 정렬
  public char[] sort(String text) {
    char[] textList = new char[lenght];

    //char 배열 만들기
    for(int i =0; i < lenght;i++) { 
      textList[i] = text.charAt(i);
    }

    //배열 정렬
    for(int i =0; i < lenght;i++ ) {
      for(int j=i+1;j<lenght;j++) {
        char temp;
        if(textList[i] < textList[j]) {
          temp = textList[i];
          textList[i] = textList[j];
          textList[j] = temp;
        }
      }
    } 
    return textList;
  }


}

package fr_departments;
public class FR_Department {
  private int number ;
  private String code ;
  private String name ;
  private String prefecture ;
  private String[] subprefecture ;

  public FR_Department (
     String code, String name, String prefecture,
     String... subprefectures) {
    setNumber(Integer.parseInt(code)) ;
    setCode(code) ;
    setName(name) ;
    setPrefecture(prefecture) ;
    setSubprefectureCount(subprefectures.length) ;
    for (int i = 0 ; i < subprefectures.length ; i++) {
      setSubprefecture(i, subprefectures[i]) ;
    }
  }
  
  public static void setDepartment
    (FR_Department department,
     String code, String name, String prefecture,
     String... subprefectures) {
    department.setNumber(Integer.parseInt(code)) ;
    department.setCode(code) ;
    department.setName(name) ;
    department.setPrefecture(prefecture) ;
    department.setSubprefectureCount(subprefectures.length) ;
    for (int i = 0 ; i < subprefectures.length ; i++) {
      department.setSubprefecture(i, subprefectures[i]) ;
    }
  }
  
  public int getNumber() {
    return number ;
  }
  public String getCode() {
    return code ;
  }
  public String getName() {
    return name ;
  }
  public String getPrefecture() {
    return prefecture ;
  }
  public int getSubprefectureCount() {
    return this.subprefecture.length ;
  }
  public String getSubprefecture(int rank) {
    return this.subprefecture[rank] ;
  }
  
  public void setNumber(int number) {
    this.number = number ;
  }
  public void setCode(String code) {
    this.code = code ;
  }
  public void setName(String name) {
    this.name = name ;
  }
  public void setPrefecture(String prefecture) {
    this.prefecture = prefecture ;
  }
  public void setSubprefectureCount(int count) {
    this.subprefecture = new String[count] ;
  }
  public void setSubprefecture(int rank, String name) {
    this.subprefecture[rank] = name ;
  }
  
}

package iloveyouboss.domain;

import java.util.List;

public interface Question {
   int id();
   List<String> options();
   String text();

   String AnswerNotProvided = "AnswerNotProvided";
}
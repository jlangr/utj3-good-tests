package iloveyouboss.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static iloveyouboss.domain.questions.YesNoQuestion.No;
import static iloveyouboss.domain.questions.YesNoQuestion.Yes;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ACriteria {
   int questionId = 100;
   Criterion criterion1 = new Criterion(1, questionId, Yes);
   Criterion criterion2 = new Criterion(2, questionId, No);
   Criterion criterion3 = new Criterion(3, questionId, No);

   @Test
   void holdsACollectionOfCriterion() {
      var criteria = new Criteria(List.of(criterion1, criterion2));

      assertEquals(List.of(criterion1, criterion2), listOfCriterion(criteria));
   }

   @Test
   void canBeConstructedWithVarargs() {
      var criteria = new Criteria(criterion1, criterion2);

      assertEquals(List.of(criterion1, criterion2), listOfCriterion(criteria));
   }

   @Test
   void canAcceptAdditionalCriterion() {
      var criteria = new Criteria(criterion1);

      criteria.add(criterion2);
      criteria.add(criterion3);

      assertEquals(List.of(criterion1, criterion2, criterion3), listOfCriterion(criteria));
   }

   private static List<Criterion> listOfCriterion(Criteria criteria) {
      return criteria.stream().toList();
   }
}
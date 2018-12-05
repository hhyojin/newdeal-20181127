package com.eomcs.lms.dao;

import java.util.List;
import com.eomcs.lms.domain.Lesson;

public interface LessonDao {

  List<Lesson> findAll() throws Exception;

  Lesson findByNo(int no) throws Exception;

  int insert(Lesson lesson) throws Exception;

  int update(Lesson lesson) throws Exception;

  int delete(int no) throws Exception;
}

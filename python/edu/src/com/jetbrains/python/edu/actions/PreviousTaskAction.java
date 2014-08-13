package com.jetbrains.python.edu.actions;


import com.jetbrains.python.edu.editor.StudyEditor;
import com.jetbrains.python.edu.course.Task;

import javax.swing.*;

public class PreviousTaskAction extends TaskNavigationAction {

  @Override
  protected JButton getButton(StudyEditor selectedStudyEditor) {
    return selectedStudyEditor.getPrevTaskButton();
  }

  @Override
  protected String getNavigationFinishedMessage() {
    return "It's already the first task";
  }

  @Override
  protected Task getTargetTask(Task sourceTask) {
    return sourceTask.prev();
  }
}
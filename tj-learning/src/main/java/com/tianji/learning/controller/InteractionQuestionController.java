package com.tianji.learning.controller;


import com.tianji.api.dto.exam.QuestionDTO;
import com.tianji.common.domain.dto.PageDTO;
import com.tianji.learning.domain.dto.QuestionFormDTO;
import com.tianji.learning.domain.query.QuestionAdminPageQuery;
import com.tianji.learning.domain.query.QuestionPageQuery;
import com.tianji.learning.domain.vo.QuestionAdminVO;
import com.tianji.learning.domain.vo.QuestionVO;
import com.tianji.learning.service.IInteractionQuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 互动提问的问题表 前端控制器
 * </p>
 *
 * @author 晟哥
 * @since 2026-05-15
 */
@RestController
@RequestMapping("/questions")
@Api(tags = "互动问答相关接口")
@RequiredArgsConstructor
public class InteractionQuestionController {

  private final IInteractionQuestionService questionService;


  @PostMapping
  @ApiOperation("新增互动问题")
    public void saveQuestion(@Valid @RequestBody QuestionFormDTO questionDTO){
    questionService.saveQuestion(questionDTO);
  }
  @PutMapping("/{id}")
  @ApiOperation("修改互动问题")
  private void updateQuestion(@Valid @RequestBody QuestionFormDTO questionDTO, @PathVariable Long id){
    questionService.updateQuestion(questionDTO,id);
  }

  @ApiOperation("分页查询互动问题")
  @GetMapping("page")
  public PageDTO<QuestionVO> queryQuestionPage(QuestionPageQuery query){
    return questionService.queryQuestionPage(query);
  }

  @ApiOperation("根据id查询问题详情")
  @GetMapping("/{id}")
  public QuestionVO queryQuestionById(@ApiParam(value = "问题id", example = "1") @PathVariable("id") Long id){
    return questionService.queryQuestionById(id);
  }
}

package com.tianji.learning.controller;


import com.tianji.common.domain.dto.PageDTO;
import com.tianji.common.domain.query.PageQuery;
import com.tianji.learning.domain.dto.LearningPlanDTO;
import com.tianji.learning.domain.vo.LearningLessonVO;
import com.tianji.learning.domain.vo.LearningPlanPageVO;
import com.tianji.learning.service.ILearningLessonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 学生课程表 前端控制器
 * </p>
 *
 * @author 晟哥
 * @since 2026-05-11
 */
@Api(tags = "我的课表相关接口")
@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LearningLessonController {

    private final ILearningLessonService lessonService;



    /**
     * 校验当前用户是否可以学习当前课程
     * @param courseId 课程id
     * @return lessonId，如果是报名了则返回lessonId，否则返回空
     */
    @ApiOperation("检查当前用户是否可以学习该课程")
    @GetMapping("/{courseId}/valid")
    public Long isLessonVaild(@PathVariable("courseId") Long courseId){
        return lessonService.isLessonVaild(courseId);
    }

    @ApiOperation("查询用户课表中指定课程状态")
    @GetMapping("/{courseId}")
    public LearningLessonVO queryLessonByCourseId(@PathVariable("courseId") Long courseId) {
        return lessonService.queryLessonByCourseId(courseId);
    }


    @ApiOperation("查询我的课表，排序字段 latest_learn_time:学习时间排序，create_time:购买时间排序")
    @GetMapping("/page")
    public PageDTO<LearningLessonVO> queryMyLessons(PageQuery query) {
        return lessonService.queryMyLessons(query);
    }
    @GetMapping("/now")
    @ApiOperation("查询我正在学习的课程")
    public LearningLessonVO queryMyCurrentLesson() {
        return lessonService.queryMyCurrentLesson();
    }
    @ApiOperation("创建学习计划")
    @PostMapping("/plans")
    public void createLearningPlans(@Valid @RequestBody LearningPlanDTO planDTO){
        lessonService.createLearningPlan(planDTO.getCourseId(), planDTO.getFreq());
    }
    @ApiOperation("查询我的学习计划")
    @GetMapping("/plans")
    public LearningPlanPageVO queryMyPlans(PageQuery query){
        return lessonService.queryMyPlans(query);
    }
}

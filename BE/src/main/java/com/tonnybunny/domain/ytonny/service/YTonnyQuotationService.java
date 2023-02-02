package com.tonnybunny.domain.ytonny.service;


import com.tonnybunny.common.dto.QuotationStateCodeEnum;
import com.tonnybunny.domain.user.entity.UserEntity;
import com.tonnybunny.domain.user.repository.UserRepository;
import com.tonnybunny.domain.ytonny.dto.YTonnyQuotationRequestDto;
import com.tonnybunny.domain.ytonny.entity.YTonnyEntity;
import com.tonnybunny.domain.ytonny.entity.YTonnyQuotationEntity;
import com.tonnybunny.domain.ytonny.entity.YTonnyQuotationImageEntity;
import com.tonnybunny.domain.ytonny.repository.YTonnyQuotationRepository;
import com.tonnybunny.domain.ytonny.repository.YTonnyRepository;
import com.tonnybunny.exception.CustomException;
import com.tonnybunny.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class YTonnyQuotationService {

	private final UserRepository userRepository;
	private final YTonnyRepository yTonnyRepository;
	private final YTonnyQuotationRepository yTonnyQuotationRepository;

	private final String uploadFolder = "static" + File.separator + "ytonny" + File.separator + "quotation";

	@Value("${app.file-upload.upload-path}")
	private String uploadPath;


	/**
	 * MEMO : CREATE
	 * MARK : 예약통역 견적서를 생성
	 *
	 * @param yTonnyQuotationRequestDto : 예약통역 공고 관련 정보
	 * @param yTonnySeq                 : 대상 예약통역 공고 seq
	 * @return 생성된 예약통역 공고 seq
	 */
	@Transactional
	public Long createYTonnyQuotation(Long yTonnySeq, YTonnyQuotationRequestDto yTonnyQuotationRequestDto, MultipartHttpServletRequest request) {

		System.out.println("YTonnyQuotationService.createYTonnyQuotation");

		// param setting
		Long clientSeq = yTonnyQuotationRequestDto.getClientSeq();

		Long helperSeq = yTonnyQuotationRequestDto.getHelperSeq();

		LocalDateTime startDate = yTonnyQuotationRequestDto.getStartDate();

		// find
		UserEntity clientEntity = userRepository.findById(clientSeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
		UserEntity helperEntity = userRepository.findById(helperSeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
		YTonnyEntity yTonnyEntity = yTonnyRepository.findById(yTonnySeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));

		// dto -> entity
		YTonnyQuotationEntity yTonnyQuotationEntity = YTonnyQuotationEntity.builder()
		                                                                   .yTonny(yTonnyEntity)
		                                                                   .client(clientEntity)
		                                                                   .helper(helperEntity)
		                                                                   .unitPrice(yTonnyQuotationRequestDto.getUnitPrice())
		                                                                   .quotationStateCode(QuotationStateCodeEnum.미선택)
		                                                                   .startDate(yTonnyQuotationRequestDto.getStartDate())
		                                                                   .endDate(yTonnyQuotationRequestDto.getEndDate())
		                                                                   .title(yTonnyEntity.getTitle())
		                                                                   .content(yTonnyEntity.getContent())
		                                                                   .endLangCode(yTonnyEntity.getEndLangCode())
		                                                                   .startLangCode(yTonnyEntity.getStartLangCode())
		                                                                   .estimateDate(yTonnyEntity.getEstimateDate())
		                                                                   .estimateStartTime(yTonnyEntity.getEstimateStartTime())
		                                                                   .estimateTime(yTonnyEntity.getEstimateTime())
		                                                                   .build();

		// save
		Long yTonnyQuotationSeq = yTonnyQuotationRepository.save(yTonnyQuotationEntity).getSeq();

		// image save
		try {

			List<YTonnyQuotationImageEntity> yTonnyQuotationImageEntityList = createYTonnyQuotationImageList(yTonnyQuotationSeq, request);
			System.out.println("yTonnyQuotationImageEntityList = " + yTonnyQuotationImageEntityList);

			yTonnyQuotationEntity.yTonnyQuotationImageList(yTonnyQuotationImageEntityList); // image set
			yTonnyQuotationRepository.save(yTonnyQuotationEntity); // save

		} catch (Exception e) {
			System.out.println("파일 미생성 오류!!!!!!!!!!!!!!!!");
		}

		return yTonnyQuotationSeq;

	}


	/**
	 * MARK : 예약통역 견적서 이미지 파일 업로드
	 *
	 * @param yTonnyQuotationSeq : 대상 견적서 seq
	 * @param request
	 * @return 파일로 저장된 예약통역 견적서 이미지 리스트
	 */
	private List<YTonnyQuotationImageEntity> createYTonnyQuotationImageList(Long yTonnyQuotationSeq, MultipartHttpServletRequest request) {

		System.out.println("YTonnyQuotationService.createYTonnyQuotationImageList");

		List<YTonnyQuotationImageEntity> yTonnyQuotationImageEntityList = new ArrayList<>();

		try {
			// upload file path setting
			// ex) /static/upload/ytonny/quotation
			File uploadDir = new File(uploadPath + File.separator + uploadFolder);
			if (!uploadDir.exists()) uploadDir.mkdirs();

			// form file data 가져오기
			List<MultipartFile> fileList = request.getFiles("file");

			if (!fileList.isEmpty() && fileList != null) {

				for (MultipartFile partFile : fileList) {

					// file name
					String originalFilename = partFile.getOriginalFilename(); // file origin name
					String extension = FilenameUtils.getExtension(originalFilename); // extract file extension
					UUID uuid = UUID.randomUUID(); // generate Random File UUID
					String fileName = uuid + "." + extension; // 실제 저장할 file name

					// file object
					String filePath = uploadFolder + File.separator + fileName;
					File saveFile = new File(uploadPath + File.separator + filePath);

					// file save
					partFile.transferTo(saveFile);

					// find quotation
					YTonnyQuotationEntity yTonnyQuotationEntity = yTonnyQuotationRepository.findById(yTonnyQuotationSeq)
					                                                                       .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));

					// image file entity
					YTonnyQuotationImageEntity yTonnyQuotationImageEntity = YTonnyQuotationImageEntity.builder()
					                                                                                  .yTonnyQuotation(yTonnyQuotationEntity)
					                                                                                  .imageName(originalFilename)
					                                                                                  .imageContentType(partFile.getContentType())
					                                                                                  .imageFileSize(partFile.getSize())
					                                                                                  .imagePath(filePath)
					                                                                                  .build();

					// list add
					yTonnyQuotationImageEntityList.add(yTonnyQuotationImageEntity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

		// entity list return
		return yTonnyQuotationImageEntityList;
	}


	/**
	 * MEMO : READ
	 * MARK : 번역 견적서 목록 조회 with pagination
	 *
	 * @param yTonnySeq                 : 예약통역 공고 관련 정보
	 * @param yTonnyQuotationRequestDto : pagination
	 * @return 생성된 예약통역 공고 seq
	 */
	public List<YTonnyQuotationEntity> getYTonnyQuotationList(Long yTonnySeq, YTonnyQuotationRequestDto yTonnyQuotationRequestDto) {

		System.out.println("YTonnyQuotationService.getYTonnyQuotationList");

		// param setting
		int page = yTonnyQuotationRequestDto.getPage();
		int size = yTonnyQuotationRequestDto.getSize();

		// pagination
		Pageable pageable = PageRequest.of(page, size);

		return yTonnyQuotationRepository.findByyTonnySeqOrderByCreatedAtDesc(yTonnySeq, pageable).getContent();
	}


	/**
	 * MARK : 번역 견적서 상세 조회
	 *
	 * @param yTonnyQuotationSeq : 대상 견적서 seq
	 * @return 견적서 내용 반환
	 */
	public YTonnyQuotationEntity getYTonnyQuotationDetail(Long yTonnyQuotationSeq) {

		System.out.println("YTonnyQuotationService.getYTonnyQuotationDetail");

		// find
		return yTonnyQuotationRepository.findById(yTonnyQuotationSeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));

	}


	/**
	 * MEMO : UPDATE
	 * MARK : 예약통역 견적서의 상태를 수정
	 *
	 * @param yTonnySeq                 : 대상 예약통역 seq
	 * @param yTonnyQuotationRequestDto : 대상 견적서 관련 정보
	 * @return 수정된 견적서 seq
	 */
	public Long modifyYTonnyQuotationState(Long yTonnySeq, YTonnyQuotationRequestDto yTonnyQuotationRequestDto) {

		System.out.println("YTonnyQuotationService.modifyYTonnyQuotationState");

		// param setting
		Long yTonnyQuotationSeq = yTonnyQuotationRequestDto.getYTonnyQuotationSeq();
		QuotationStateCodeEnum quotationStateCodeEnum = yTonnyQuotationRequestDto.getQuotationStateCode();

		// find
		YTonnyQuotationEntity yTonnyQuotationEntity = yTonnyQuotationRepository.findById(yTonnyQuotationSeq)
		                                                                       .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));

		// 수정
		yTonnyQuotationEntity.quotationStateCode(quotationStateCodeEnum);

		// save
		return yTonnyQuotationRepository.save(yTonnyQuotationEntity).getSeq();

	}

}

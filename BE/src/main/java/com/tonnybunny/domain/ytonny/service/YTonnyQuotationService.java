package com.tonnybunny.domain.ytonny.service;


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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class YTonnyQuotationService {

	private final UserRepository userRepository;
	private final YTonnyRepository yTonnyRepository;
	private final YTonnyQuotationRepository yTonnyQuotationRepository;

	private final String uploadFolder = "ytonny" + File.separator + "quotation";

	@Value("${app.fileupload.uploadPath}")
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
		                                                                   .quotationStateCode(yTonnyQuotationRequestDto.getQuotationStateCode())
		                                                                   .build();

		// save
		Long yTonnyQuotationSeq = yTonnyQuotationRepository.save(yTonnyQuotationEntity).getSeq();

		// image list load
		// image load
		// create 하고
		// get 해서 entity 만들기 (어케 하지?)
		try {
			List<YTonnyQuotationImageEntity> yTonnyQuotationImageEntityList = createYTonnyQuotationImageList(yTonnyQuotationSeq, request);
		} catch (Exception e) {
			System.out.println("오류입니도");
		}

		return yTonnyQuotationSeq;

	}


	private List<YTonnyQuotationImageEntity> createYTonnyQuotationImageList(Long yTonnyQuotationSeq, MultipartHttpServletRequest request) throws Exception {

		System.out.println("YTonnyQuotationService.createYTonnyQuotationImageList");

		List<YTonnyQuotationImageEntity> yTonnyQuotationImageEntityList = new ArrayList<>();

		// upload file path setting
		// ex) /static/upload/ytonny/quotation
		File uploadDir = new File(uploadPath + File.separator + uploadFolder);
		if (!uploadDir.exists()) uploadDir.mkdir();

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

		// entity list return
		return yTonnyQuotationImageEntityList;
	}

	//	public List<YTonnyQuotationEntity> getYTonnyQuotationList(Long yTonnySeq) {
	//	}
	//
	//
	//	public YTonnyQuotationEntity getYTonnyQuotationDetail(Long yTonnyQuotationSeq) {
	//	}
	//
	//
	//	public Long modifyYTonnyQuotationState(YTonnyQuotationRequestDto yTonnyQuotationRequestDto) {
	//	}

}

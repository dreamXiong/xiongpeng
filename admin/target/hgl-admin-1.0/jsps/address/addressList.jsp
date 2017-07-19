<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="table table-bordered table-hover dataTable text-center table-bordered">
	<tr class="info">
	  <th>省编码</th>
	  <th>省名称</th>
	  <th>城市编码</th>
	  <th>城市名称</th>
	  <th>区县编码</th>
	  <th>区县名称</th>
	  <th>街道编码</th>
	  <th>街道名称</th>	  
	  <th>操作</th>
	</tr>
                      
	<c:forEach items="${list}" var="addressDto">
		<tr>	
			<td id="provinceId${addressDto.provinceId}">${addressDto.provinceId}</td>
			<td>${addressDto.provinceName}</td>	
			<td id="cityId${addressDto.cityId}">${addressDto.cityId}</td>
			<td>${addressDto.cityName}</td>				
			<td id="countryId${addressDto.countryId}">${addressDto.countryId}</td>
			<td>${addressDto.countryName}</td>
			<td id="streetId${addressDto.streetId}">${addressDto.streetId}</td>
			<td id="streetName${addressDto.streetId}">${addressDto.streetName}</td>			
			<td>
				<a href="javascript:void(0)" class="btn btn-primary btn-xs" onclick="updateItem(${addressDto.provinceId},${addressDto.cityId},${addressDto.countryId},${addressDto.streetId})">修改</a>
				<a href="javascript:void(0)" class="btn btn-primary btn-xs" onclick="deleteStreet(${addressDto.streetId})">删除</a>
				
			</td>
		</tr>
	</c:forEach>   
</table>
<liguo:pagination pageAction="doSearchResult"/>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="//cdn.jsdelivr.net/jquery/1.10.2/jquery-1.10.2.min.js"></script>
  
<link type="text/css" rel="stylesheet" href="//cdn.jsdelivr.net/jquery.ui/1.9.2/themes/base/jquery.ui.all.css" />
 
<link type="text/css" rel="stylesheet" href="//cdn.jsdelivr.net/jqgrid/4.5.2/css/ui.jqgrid.css" />
<script type="text/javascript" src="//cdn.jsdelivr.net/jqgrid/4.5.2/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="resources/js/grid.locale-kr.js"></script>
 
 <script type="text/javascript" src="//cdn.jsdelivr.net/jqgrid/4.5.2/plugins/grid.addons.js"></script>
<script type="text/javascript" src="//cdn.jsdelivr.net/jqgrid/4.5.2/plugins/grid.postext.js"></script>
<script type="text/javascript" src="//cdn.jsdelivr.net/jqgrid/4.5.2/plugins/grid.setcolumns.js"></script>
<script type="text/javascript" src="//cdn.jsdelivr.net/jqgrid/4.5.2/plugins/jquery.contextmenu.js"></script>
<script type="text/javascript" src="//cdn.jsdelivr.net/jqgrid/4.5.2/plugins/jquery.searchFilter.js"></script>
<script type="text/javascript" src="//cdn.jsdelivr.net/jqgrid/4.5.2/plugins/jquery.tablednd.js"></script>
<link type="text/css" rel="stylesheet" href="//cdn.jsdelivr.net/jqgrid/4.5.2/plugins/searchFilter.css" />
<link type="text/css" rel="stylesheet" href="//cdn.jsdelivr.net/jqgrid/4.5.2/plugins/ui.multiselect.css" />

 
 
</head>
<body>

<script >
    $(document).ready(function() {
    	
    	var lastsel2;
        $("#grid").jqGrid({
        	url:'http://localhost:8080/controller/all.json',
            caption : 'Daum SNS Pic',    // caption : 그리드의 제목을 지정한다.
            datatype : 'json',               // datatype : 데이터 타입을 지정한다.
                                                    // (json 으로 외부에 요청을 보내면 보안정책에 위배되어 요청이 나가질 않는다.
                                                    //  따라서 datatype 을 jsonp로 변경하고 보내야 한다.)
            mtype : 'get',                     // mtype : 데이터 전송방식을 지정한다.
            height : '300px',                 // height : 그리드의 높이를 지정한다.
            pager : '#pager',               // pager : 도구 모임이 될 div 태그를 지정한다.
            rowNum : 10,                      // rowNum : 한 화면에 표시할 행 개수를 지정한다.
            loadonce : true,                // loadonce : rowNum 설정을 사용하기 위해서 true로 지정한다.
            rowList : [10, 20, 30, 100],       // rowList : rowNum을 선택할 수 있는 옵션을 지정한다.
			/* 
            onSelectRow : function(id){
				alert("id : " + id + "<br>" + $("#grid").jqGrid("getCell",id,1) );
				//alert(id);
			},
             */
			// colNames : 열의 이름을 지정한다.
			onSelectRow : function(id){
				//alert(id);
				//alert("id : " + id + "<br>" + $("#grid").jqGrid("getCell",id,1) );
				
				//alert( $("#grid").jqGrid("getCell",id,1) );
				
				if(id && id!=lastsel2){
					
					var last_row = lastsel2;
					/*
					Update 부분까지만 구현
					Update 후 refresh 부분은 찾는중..
					(페이지 refresh 안하고 처리할 방법..)
					*/
					$('#grid').saveRow(lastsel2,
							function(data){
						//alert(eval(data));
						//alert( $.type(data) );
						var obj = data;
						var data = '';
						for(var attr in obj){
							if(typeof(obj[attr]) == 'string'|| typeof(obj[attr])=='number'){
								data = data + 'Attr Name : ' + attr + ', Value : ' + obj[attr] + ', Type : ' + typeof(obj[attr]);
							} else{
								data = data + 'Attr Name : ' + attr + ', Type : ' + typeof(obj[attr]) + '\n';
							} 
							
							//alert(data);
						}
						
						
					}
					,"pu",{
						"id" : lastsel2
					}
					);//end save Row
					
					//$('#grid').restoreRow(lastsel2);
			        $('#grid').editRow(id,true);
			        
			          lastsel2 = id;
			          
			      }//end if
					
				
				
					//$("#grid").editRow(id,true);
			},
            colNames : [ '글번호', '제목', '글쓴이', '조회수', '유효여부' ,'글 내용'],
            colModel : [
                        { name : 'id',            index : 'id',                width : 40,        align : 'center' },
                        { name : 'subject',         index : 'subject', editable: true,         width : 100,    align : 'left'   },
                        { name : 'writer',             index : 'writer',                width : 100,    align : 'left'   },
                        { name : 't_count',         index : 't_count',            width : 200,    align : 'left'   },
                        { name : 'valid',             index : 'valid',            width : 100,    align : 'center' },
                        { name : 't_description',    index : 't_description',  width : 100,    align : 'center' }
                       ]
            
            // jqGrid 에서 default 형태의 JSON 이 아닌 소셜픽만의 고유의 json 형태로 날아오기에.
            // 소셜픽의 API 를 바꿀 순 없어, jqGrid 에서 json을 
            // customizing하여 사용하기 위해 jsonReader를 사용했다.
            /**/ 
            ,jsonReader : {
                            repeatitems : false,
                            id : "id", // id 로 사용할 컬럼
                            //root : function (obj) { return obj.socialpick.item; },//시작 지점
                            page : function (obj) { return 1; }, // 표시할 page
                            total : function (obj) { return 1; },//아직 모르겠네
                           // records : function (obj) {return obj.socialpick.item.length; }//아직 모르겠네
            }
 
        // navGrid() 메서드는 검색 및 기타기능을 사용하기위해 사용된다.
        }).navGrid('#pager', {
            /**/ 
        	search : true,
            edit : true,
            add : true,
            del : true
            
            
             
        });
    });
</script>


<table id="grid"></table>
<div id = "pager"></div>

</body>
</html>
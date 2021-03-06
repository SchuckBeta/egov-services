var dat = {
	"wc.create": {
		"numCols": 12/3,
		"url":  "/wcms/masters/meterStatus/_create",
		"tenantIdRequired": true,
		"idJsonPath": "MeterStatus[0].code",
		"useTimestamp": true,
		"objectName": "MeterStatus",
		"groups": [
			{
				"label": "wc.create.MeterStatus.title",
				"name": "createMeterStatus",
				"fields": [
						{
							"name": "name",
							"jsonPath": "MeterStatus[0].meterStatus",
							"label": "wc.create.meterStatus",
							"pattern": "^[\s.]*([^\s.][\s.]*){0,100}$",
							"type": "text",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Length minimum is 3 and maximum is 100"
						},
						{
							"name": "description",
							"jsonPath": "MeterStatus[0].description",
							"label": "wc.create.description",
							"pattern": "^[\s.]*([^\s.][\s.]*){0,250}$",
							"type": "text",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Length is more than 250"
						},
						{
							"name": "Active",
							"jsonPath": "MeterStatus[0].active",
							"label": "wc.create.active",
							"pattern": "",
							"type": "checkbox",
							"isRequired": false,
							"defaultValue":true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						}
				]
			}
		]
	},
	"wc.search": {
		"numCols": 12/3,
		"url": "/wcms/masters/meterStatus/_search",
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "SearchMeterStatus",
		"groups": [
			{
				"label": "wc.search.meterStatus.title",
				"name": "createCategoryType",
				"fields": [
						{
							"name": "name",
							"jsonPath": "meterStatus",
							"label": "wc.create.meterStatus",
							"pattern": "^[\s.]*([^\s.][\s.]*){0,100}$",
							"type": "text",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Length is more than 100"
						},
						{
							"name": "Active",
							"jsonPath": "active",
							"label": "wc.create.active",
							"pattern": "",
							"type": "checkbox",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						}
				]
			}
		],
		"result": {
			"header": [{label: "wc.create.meterStatus"}, {label: "wc.search.result.description"}, {label: "wc.search.result.active"}],
			"values": ["meterStatus", "description", "active"],
			"resultPath": "MeterStatus",
			"rowClickUrlUpdate": "/update/wc/meterStatus/{id}",
			"rowClickUrlView": "/view/wc/meterStatus/{id}"
			}
	},
	"wc.view": {
		"numCols": 12/3,
		"url": "/wcms/masters/meterStatus/_search?ids={id}",
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "MeterStatus",
		"groups": [
			{
				"label": "wc.view.MeterStatus.title",
				"name": "MeterStatus",
				"fields": [
						{
							"name": "name",
							"jsonPath": "MeterStatus[0].meterStatus",
							"label": "wc.create.meterStatus",
							"pattern": "",
							"type": "text",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						},
						{
							"name": "description",
							"jsonPath": "MeterStatus[0].description",
							"label": "wc.create.description",
							"pattern": "",
							"type": "text",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						},
						{
							"name": "Active",
							"jsonPath": "MeterStatus[0].active",
							"label": "wc.create.active",
							"pattern": "",
							"type": "checkbox",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						}
				]
			}
		]
	},
	"wc.update": {
		"numCols": 12/3,
		"searchUrl": "/wcms/masters/meterStatus/_search?ids={id}",
		"url":"/wcms/masters/meterStatus/_update",
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "MeterStatus",
		"groups": [
			{
				"label": "wc.update.MeterStatus.title",
				"name": "UpdateMeterStatus",
				"fields": [
						{
							"name": "name",
							"jsonPath": "MeterStatus[0].meterStatus",
							"label": "wc.create.meterStatus",
							"pattern": "^[\s.]*([^\s.][\s.]*){0,100}$",
							"type": "text",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Length is more than 100"
						},
						{
							"name": "description",
							"jsonPath": "MeterStatus[0].description",
							"label": "wc.create.description",
							"pattern": "^[\s.]*([^\s.][\s.]*){0,250}$",
							"type": "text",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Length is more than 250"
						},
						{
							"name": "Active",
							"jsonPath": "MeterStatus[0].active",
							"label": "wc.create.active",
							"pattern": "",
							"type": "checkbox",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						}
				]
			}
		]
	}
}

export default dat;

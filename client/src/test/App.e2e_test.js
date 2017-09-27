Feature('Home Page')

Scenario('user can Add a space', (I) => {
    I.amOnPage('/')
    I.dontSee('jones 0%')
    I.click('Add Space')
    I.fillField('Name', 'jones')
    I.fillField('Memory', '10')
    I.fillField('Disk', '100')
    I.click('Create')
    I.see('jones 0%')
})